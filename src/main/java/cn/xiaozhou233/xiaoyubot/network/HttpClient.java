package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.*;

import java.io.IOException;

public class HttpClient {
    private static final OkHttpClient CLIENT = new OkHttpClient();

    // Send GET request
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
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
                throw new IOException("Unexpected response code: " + response.code());
            }
            return response.body().string();
        }
    }
}
