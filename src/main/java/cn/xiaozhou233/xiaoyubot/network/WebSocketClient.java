package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.*;

public class WebSocketClient {
    private final OkHttpClient client;
    private Request request;
    private final WSListener listener;

    public WebSocketClient(String url) {
        client = new OkHttpClient();
        request = new Request.Builder().url(url).build();
        this.listener = new WSListener(url, this);
    }

    public void start() {
        client.newWebSocket(request, this.listener);
    }
}
