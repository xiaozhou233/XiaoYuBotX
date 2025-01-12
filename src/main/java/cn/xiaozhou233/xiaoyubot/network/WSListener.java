package cn.xiaozhou233.xiaoyubot.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;

public class WSListener extends WebSocketListener {
    private static final int RETRY_TIMES = 5;
    private static final int RETRY_INTERVAL = 3000; // milliseconds
    private int retry = 0;
    private final OkHttpClient client;
    private final String url;
    public WebSocket webSocket;

    public WSListener(OkHttpClient client, String url) {
        this.client = client;
        this.url = url;
    }

    public void connect() {
        Request request = new Request.Builder().url(url).build();
        webSocket = client.newWebSocket(request, this);
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        System.out.println("WebSocket connected successfully.");
        retry = 0; // Reset retry counter upon successful connection
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        System.out.println("Received message: " + text);
        // Handle the received message here
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
        System.err.println("WebSocket connection failed: " + t.getMessage());
        attemptReconnect();
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        System.out.println("WebSocket is closing: " + reason);
        webSocket.close(code, reason);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        System.out.println("WebSocket closed: " + reason);
        attemptReconnect();
    }

    private void attemptReconnect() {
        if (retry < RETRY_TIMES) {
            retry++;
            System.out.println("Reconnecting attempt " + retry + "...");
            try {
                Thread.sleep(RETRY_INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Reconnect delay interrupted.");
                return;
            }
            connect();
        } else {
            System.err.println("Max reconnect attempts reached. Giving up.");
        }
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }
}
