package cn.xiaozhou233.xiaoyubot.network;

import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class CallAPI {
    private static final String HTTP_API = XiaoYuBotX.httpUrl;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final HttpClient HTTP_CLIENT = new HttpClient();
    private static final Logger logger = LoggerFactory.getLogger("OneBotAPI");

    public static void call(String action, Map<String, Object> params) {
        System.out.println(params);
        try {
            String jsonParams = OBJECT_MAPPER.writeValueAsString(params);
            String httpResponse = HTTP_CLIENT.post(String.format("%s/%s", HTTP_API, action), jsonParams);
            JsonNode responseJson = OBJECT_MAPPER.readTree(httpResponse);

            // Log the status and retcode from the response JSON
            if (responseJson.get("status").asText().equals("ok")){
                logger.info("API {} call successful", action);
            }else {
                logger.info("Unknown Status: Status: {}, Retcode: {}", responseJson.get("status"), responseJson.get("retcode"));
            }
        } catch (IOException e) {
            logger.error("Failed to call API {} with params: {}", action, params);
            logger.error(String.valueOf(e));
        }
    }
}
