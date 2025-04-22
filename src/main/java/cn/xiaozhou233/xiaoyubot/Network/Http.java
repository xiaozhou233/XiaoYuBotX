package cn.xiaozhou233.xiaoyubot.Network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Http {
    private static final Logger logger = LoggerFactory.getLogger("Http");
    private final ObjectMapper mapper = new ObjectMapper();
    private final OkHttpClient client;
    public Http(){
        this.client = new OkHttpClient();
    }

    public String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            logger.debug("HTTP GET: [{}] {} ", response.code(), url);
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                logger.error("HTTP GET: [{}] {} ",response.code(), url);
            }
        } catch (Exception e) {
            logger.error("Http Error: [{}] {}", url, e.getMessage(), e.getCause());
        }
        return null;
    }

    public String post(String url, String json) {
        RequestBody requestBody = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            logger.debug("HTTP POST: [{}] {} ", response.code(), url);
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                logger.error("HTTP POST: [{}] {} ",response.code(), url);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String post(String url, Map<?,?> params) {
        try {
            String json = mapper.writeValueAsString(params);
            return post(url, json);
        } catch (JsonProcessingException e) {
            logger.error("JSON Processing Exception: {}", e.getCause(), e);
        }
        return null;
    }
}
