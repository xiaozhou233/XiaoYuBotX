package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.*;

public class WebSocketClient {
    private OkHttpClient client;
    private WebSocket webSocket;

    public WebSocketClient() {
        // Lazy initialization
        this.client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
    }

    public void connect(String url) {
        Request request = new Request.Builder().url(url).build();
        this.webSocket = client.newWebSocket(request, new WsListener());
    }

    public void send(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        } else {
            System.err.println("[WARN] Cannot send message, WebSocket is not connected.");
        }
    }

    public void close() {
        if (webSocket != null) {
            webSocket.close(1000, "Closing connection");
            webSocket = null;
        }
        if (client != null) {
            client.dispatcher().executorService().shutdown();
            client = null;
        }
    }
}
