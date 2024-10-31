package cn.xiaozhou233.xiaoyubot.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class CallAPI {
    private static String HTTP_API = "http://127.0.0.1:3000";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static HttpClient httpClient = new HttpClient();


    public static void call(String action, HashMap<String, Object> params){
        try{
            String http_response = httpClient.post(String.format("%s/%s", HTTP_API, action), objectMapper.writeValueAsString(params));
            JsonNode response = objectMapper.readTree(http_response);
            System.out.println("[INFO] " + response.get("status") + " " + response.get("retcode"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
