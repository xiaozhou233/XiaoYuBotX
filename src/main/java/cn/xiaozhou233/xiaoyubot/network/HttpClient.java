package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.*;

import java.io.IOException;

public class HttpClient {
    private final OkHttpClient client;

    public HttpClient() {
        this.client = new OkHttpClient();
    }

    // 发送GET请求
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    // 发送POST请求
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        String response = new HttpClient().get("https://www.baidu.com");
        System.out.println(response);

        String json = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        String responsePost = new HttpClient().post("https://jsonplaceholder.typicode.com/posts", json);
        System.out.println("POST Response: " + responsePost);
    }
}
