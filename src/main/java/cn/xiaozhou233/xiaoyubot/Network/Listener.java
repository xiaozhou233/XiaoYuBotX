package cn.xiaozhou233.xiaoyubot.Network;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends WebSocketListener {
    private final Websocket websocketInstance;
    private int RECONNECT_TIME = 5000;
    private int MAX_RETRY = 5;
    private int retry = 0;
    private static final Logger logger = LoggerFactory.getLogger("Websocket Listener");

    public Listener(Websocket websocketInstance) {
        this.websocketInstance = websocketInstance;
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);

        this.retry = 0;
        logger.info("Websocket connected.");
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        super.onMessage(webSocket, text);

        logger.debug("Received: {}", text);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosing(webSocket, code, reason);

        logger.info("Websocket closing. code:{} reason:{}", code, reason);
        if (code == 1005){
            logger.debug("Response Code: 1005, force close.");
            webSocket.close(1001, "Server Closed");
        }
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);

        logger.info("Websocket closed. code:{} reason:{}", code, reason);

        reconnect();
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);

        if (t.getCause()!=null) {
            logger.error("Connection failed. {}", t.getCause().toString());
        }else {
            logger.error("Connection failed. ", t);
        }

        reconnect();
    }

    private void reconnect() {
        if (retry<MAX_RETRY) {
            retry++;
            logger.info("");
            logger.info("Reconnect in {} second. ({}/{})", this.RECONNECT_TIME/1000, retry, MAX_RETRY);
            try {
                Thread.sleep(this.RECONNECT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            logger.info("Reconnecting... ({}/{})", retry, MAX_RETRY);
            websocketInstance.connect();
        } else {
            logger.error("Reached max retry times. Reconnect failed. ({}/{})", retry, MAX_RETRY);
            logger.info("Exiting...");
            // TODO: Manual connect
            System.exit(1);
        }
    }
}
