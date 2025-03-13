package cn.xiaozhou233.xiaoyubot.network;

import cn.xiaozhou233.xiaoyubot.handler.WSMsgHandler;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.*;

public class WSListener extends WebSocketListener {
    private static final Logger logger = LoggerFactory.getLogger("WebSocketClient");
    private static final int MAX_RETRIES = 5;
    private static final int RETRIES_INTERVAL = 5000;
    private int retryCount = 0;
    private boolean isConnected = false;
    private String url;
    private WebSocketClient client;

    public WSListener(String url, WebSocketClient client){
        this.url = url;
        this.client = client;
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        logger.info("Connection Open");
        retryCount = 0;
        isConnected = true;
        super.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        logger.debug("Message Received: {}", text);
        WSMsgHandler handler = new WSMsgHandler(text);
        super.onMessage(webSocket, text);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        logger.warn("Connection Closing: {} {}", code, reason);
        webSocket.close(code, reason);
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        logger.warn("Connection Closed: {} {}", code, reason);
        isConnected = false;
        reconnect();
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        logger.error("Connection Failed: {}", t.getMessage());
        isConnected = false;
        reconnect();
        super.onFailure(webSocket, t, response);
    }

    private void reconnect() {
        try {
            if (retryCount < 5) {
                retryCount++;
                logger.warn("Reconnecting in {} seconds... ({})", RETRIES_INTERVAL / 1000, retryCount);
                Thread.sleep(RETRIES_INTERVAL);
                logger.warn("Reconnecting...");
                client.start();
            } else {
                logger.error("Failed to reconnect after 5 attempts. Exiting...");
                System.exit(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isConnected() {
        return isConnected;
    }
}
