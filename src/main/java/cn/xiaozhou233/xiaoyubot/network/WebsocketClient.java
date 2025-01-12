package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

public class WebsocketClient {
    private final OkHttpClient client;
    private final WSListener listener;
    private final String url;

    public WebsocketClient(String url, WSListener listener) {
        this.url = url;
        this.listener = listener;

        this.client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS) // WebSocket 需要长连接，因此设置超时为 0
                .build();

        listener.connect();
    }



    public void reconnect() {
        System.out.println("Reconnecting WebSocket...");
        if (listener.webSocket != null) {
            listener.webSocket.cancel();
        }
        listener.connect();
    }

    public void close() {
        if (listener.webSocket != null) {
            listener.webSocket.close(1000, "Client closed the connection.");
            System.out.println("WebSocket connection closed.");
        }
    }

    public void send(String message) {
        if (listener.webSocket != null) {
            listener.webSocket.send(message);
            System.out.println("Message sent: " + message);
        } else {
            System.err.println("WebSocket is not connected.");
        }
    }
}
