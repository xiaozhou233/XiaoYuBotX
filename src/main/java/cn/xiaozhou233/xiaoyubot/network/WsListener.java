package cn.xiaozhou233.xiaoyubot.network;

import cn.xiaozhou233.xiaoyubot.utils.MessageHandle;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

public class WsListener extends WebSocketListener {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TaggedLogger logger = Logger.tag("WebSocketClient");

    @Override
    public void onOpen(@NotNull WebSocket webSocket, Response response) {
        logger.info("Connected to WebSocket! [{}]", response.request().url());
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        // Parse JSON
        try {
            MessageHandle.handle(MAPPER.readTree(text));
        } catch (Exception e) {
            logger.error(e, "Failed to parse message: {}", text);
            logger.trace(e);
        }
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        logger.warn("WebSocket connection closing: {} - {}", code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, Throwable t, Response response) {
        logger.error(t, "WebSocket connection failed");
        if (response != null) {
            logger.error("Response code: {}", response.code());
        }
    }
}
