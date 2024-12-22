package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClient {
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final Logger logger = LoggerFactory.getLogger("HttpClient");

    // Send GET request
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                logger.error("Unexpected response code: {}", response.code());
            }
            return response.body().string();
        }
    }

    // Send POST request with JSON body
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                logger.error("Unexpected response code: {}", response.code());
            }
            return response.body().string();
        }
    }
}
