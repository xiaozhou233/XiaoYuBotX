package cn.xiaozhou233.xiaoyubot.network;

import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import cn.xiaozhou233.xiaoyubot.utils.MessageHandle;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WsListener extends WebSocketListener {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger("WebSocketClient");
    private static final int MAX_RETRY = XiaoYuBotX.configuration.getConfigNode().get("maxRetry").asInt();
    private static final int retryInterval = XiaoYuBotX.configuration.getConfigNode().get("retryInterval").asInt();
    private static int retryCount = 0;

    @Override
    public void onOpen(@NotNull WebSocket webSocket, Response response) {
        logger.info("Connected to WebSocket! [{}]", response.request().url());
        retryCount = 0;
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        // Parse JSON
        try {
            MessageHandle.handle(MAPPER.readTree(text));
        } catch (Exception e) {
            logger.error("Failed to parse message: {}", text);
            logger.trace(String.valueOf(e));
        }
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        logger.warn("WebSocket connection closing: {} - {}", code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
        logger.error("WebSocket connection failed {}", t.getMessage());
        if (response != null) {
            logger.error("Response code: {}", response.code());
        }

        retryCount += 1;
        if (retryCount <= MAX_RETRY) {
            logger.info("Retry in {}s... (Attempt {})", retryInterval/1000, retryCount);
            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                logger.error(String.valueOf(e));
            }
            logger.info("Retrying WebSocket connection... (Attempt {})", retryCount);
            XiaoYuBotX.webSocketClient.reconnect();
        }else {
            logger.error("failed to reconnect.");
        }
    }
}
