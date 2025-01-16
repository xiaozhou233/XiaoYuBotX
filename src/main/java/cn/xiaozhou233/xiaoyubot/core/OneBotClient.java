package cn.xiaozhou233.xiaoyubot.core;

import cn.xiaozhou233.xiaoyubot.network.HTTPClient;
import cn.xiaozhou233.xiaoyubot.network.WebSocketClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okio.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class OneBotClient {
    private static final HTTPClient httpClient = new HTTPClient();
    private final WebSocketClient event;
    private final String wsUrl;
    private static String httpUrl;
    private static final Logger logger = LoggerFactory.getLogger("OneBotClient");
    private static final ObjectMapper mapper = new ObjectMapper();

    public OneBotClient(String wsUrl, String httpUrl) {
        this.wsUrl = wsUrl;
        OneBotClient.httpUrl = httpUrl;
        event = new WebSocketClient();
    }

    public void connect() {
        event.connect(wsUrl + "/event", new EventCallback());
    }


    public static void callAPI(String action, HashMap<String, Object> params) {
        try {
            String json = mapper.writeValueAsString(params);
            String response = httpClient.post(httpUrl + "/" + action, json);
            JsonNode node = mapper.readTree(response);
            if (node.get("status").asText().equals("ok")) {
                logger.info("API {} called successfully", action);
            } else {
                logger.warn("API {} called failed: {}", action, node.get("status").asText());
            }
        } catch (IOException e) {
            logger.error("An error occurred while calling API {}",action , e);
        }
    }


}

class EventCallback implements WebSocketClient.WebSocketCallback {
    private static final Logger logger = LoggerFactory.getLogger("Event");

    @Override
    public void onOpen(Response response) {
        logger.info("Event Connected.");
    }

    @Override
    public void onMessage(String text) {
        logger.info("Received: {}", text);
        new EventHandler(text);
    }

    @Override
    public void onBinaryMessage(ByteString bytes) {

    }

    @Override
    public void onClosing(int code, String reason) {

    }

    @Override
    public void onClosed(int code, String reason) {

    }

    @Override
    public void onFailure(Throwable t, Response response) {

    }
}