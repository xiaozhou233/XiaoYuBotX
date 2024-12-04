package cn.xiaozhou233.xiaoyubot.network;

import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CallAPI {
    private static final String HTTP_API = XiaoYuBotX.httpUrl;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final HttpClient HTTP_CLIENT = new HttpClient();

    public static void call(String action, Map<String, Object> params) {
        System.out.println(params);
        try {
            String jsonParams = OBJECT_MAPPER.writeValueAsString(params);
            String httpResponse = HTTP_CLIENT.post(String.format("%s/%s", HTTP_API, action), jsonParams);
            JsonNode responseJson = OBJECT_MAPPER.readTree(httpResponse);

            // Log the status and retcode from the response JSON
            System.out.println("[INFO] Status: " + responseJson.get("status") + ", Retcode: " + responseJson.get("retcode"));
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to call API " + action + " with params: " + params);
            e.printStackTrace();
            throw new RuntimeException("API call failed: " + e.getMessage(), e);
        }
    }
}
