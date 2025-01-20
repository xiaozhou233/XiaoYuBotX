package cn.xiaozhou233.xiaoyubot.network;

import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketClient {

    private final OkHttpClient client;
    private WebSocket webSocket;

    private static final Logger logger = LoggerFactory.getLogger("WebSocketClient");
    private final int MAX_RETRY = XiaoYuBotX.getBotConfig().get("maxRetry").asInt();
    private final int RETRY_INTERVAL = XiaoYuBotX.getBotConfig().get("retryInterval").asInt();
    private int retryCount = 0;

    public WebSocketClient() {
        this.client = new OkHttpClient();
    }

    public void connect(String url, WebSocketCallback callback) {
        Request request = new Request.Builder().url(url).build();
        WebSocketListener listener = new WebSocketListener() {
            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                retryCount = 0;
                logger.info("WebSocket connected to {}", url);
                callback.onOpen(response);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                callback.onMessage(text);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                callback.onBinaryMessage(bytes);
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                callback.onClosing(code, reason);
                webSocket.close(1000, null);
            }

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                callback.onClosed(code, reason);
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
                callback.onFailure(t, response);
            }
        };

        this.webSocket = client.newWebSocket(request, listener);
    }

    private void reconnect(String url, WebSocketCallback callback) {
        if (webSocket != null) {
            webSocket.close(1000, null);
        }
        if (retryCount < MAX_RETRY) {
            logger.info("Reconnecting to WebSocket server...[{}/{}]", retryCount, MAX_RETRY);
            retryCount++;
            connect(url, callback);
        }else {
            throw new IllegalStateException("WebSocket connection failed after " + MAX_RETRY + " retries.");
        }
    }

    public void sendMessage(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        } else {
            throw new IllegalStateException("WebSocket is not connected.");
        }
    }

    public void close() {
        if (webSocket != null) {
            webSocket.close(1000, "Client is closing");
        } else {
            throw new IllegalStateException("WebSocket is not connected.");
        }
    }

    public interface WebSocketCallback {
        void onOpen(Response response);

        void onMessage(String text);

        void onBinaryMessage(ByteString bytes);

        void onClosing(int code, String reason);

        void onClosed(int code, String reason);

        void onFailure(Throwable t, Response response);
    }
}
