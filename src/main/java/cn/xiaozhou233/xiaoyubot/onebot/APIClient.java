package cn.xiaozhou233.xiaoyubot.onebot;

import cn.xiaozhou233.xiaoyubot.network.WSListener;
import cn.xiaozhou233.xiaoyubot.network.WebsocketClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.WebSocket;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;

public class APIClient{
    private static final int timeout = 10000;
    private static final int checkInterval = 3000;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger("API");
    private static final HashMap<String, Long> mqList = new HashMap<>();
    private static WebsocketClient webSocket;

    public static void run(String url) {
        url = url + "/api";
        webSocket = new WebsocketClient(url, new Listener(new OkHttpClient(), url));

        new Thread(() ->
        {
            try {
                while(true) {
                    mqList.forEach( (hash, timeStamp) -> {
                        if (System.currentTimeMillis() - timeStamp > timeout) {
                            mqList.remove(hash);
                            logger.warn("API request timeout: {} - {}", timeStamp, hash);
                        }}
                    );
                    Thread.sleep(checkInterval);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void callAPI(String name, HashMap<String, Object> param) throws JsonProcessingException {
        if (webSocket == null) {
            throw new RuntimeException("Websocket not initialized");
        } else {
            HashMap<String, Object> data = new HashMap<>();
            data.put("action", name);
            data.put("params", param);
            String hash = getHash(name +param.toString());
            data.put("echo", hash);
            String json = objectMapper.writeValueAsString(data);
            mqList.put(hash,
                    System.currentTimeMillis());
            webSocket.send(json);
            logger.info("Send API request: {}", name);
        }
    }

    public static void main(String[] args) {
        try {
            callAPI("send_private_msg", new HashMap<>(){{put("user_id", 123456789); put("message", "hello");}});
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static String getHash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class Listener extends WSListener {
        public Listener(OkHttpClient client, String url) {
            super(client, url);
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            logger.info("Connected to API server! [{}]", response.request().url());
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            logger.info("Received message: {}", text);
            try {
                JsonNode jsonNode = objectMapper.readTree(text);
                if (jsonNode.has("echo")) {
                    mqList.remove(jsonNode.get("echo").asText());
                    if (jsonNode.get("status").asText().equals("ok")) {
                        logger.info("Message sent successfully.");
                    } else {
                        logger.warn("Message sent failed: Status - {}", jsonNode.get("status").asText());
                    }
                    return;
                }

            } catch (Exception e){
                logger.error("Error while parsing JSON: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
