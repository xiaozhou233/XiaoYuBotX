package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.*;
import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

public class WebSocketClient {
    private OkHttpClient client;
    private WebSocket webSocket;
    private final String url;
    private static final TaggedLogger logger = Logger.tag("WebSocketClient");

    public WebSocketClient(String url) {
        this.url = url;
        // Lazy initialization
        this.client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
    }

    public void connect() {
        Request request = new Request.Builder().url(url).build();
        this.webSocket = client.newWebSocket(request, new WsListener());
    }

    public void send(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        } else {
            logger.warn("[WARN] WebSocket is not connected, cannot send message.");
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

    public void reconnect() {
        connect();
    }
}
