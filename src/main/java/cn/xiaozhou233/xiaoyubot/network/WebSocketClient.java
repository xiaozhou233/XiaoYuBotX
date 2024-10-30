package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.*;

public class WebSocketClient {
    private final OkHttpClient client;
    private WebSocket webSocket;

    public WebSocketClient() {
        this.client = new OkHttpClient();
    }

    public void connect(String url) {
        Request request = new Request.Builder().url(url).build();
        webSocket = client.newWebSocket(request, new WsListener());
    }

    public void send(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        }
    }

    public void close() {
        if (webSocket != null) {
            webSocket.close(1000, "Closing connection");
        }
    }

    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient();
        client.connect("ws://localhost:3001");
    }

}
