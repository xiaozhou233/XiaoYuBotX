package cn.xiaozhou233.xiaoyubot.core;

import cn.xiaozhou233.xiaoyubot.network.WebSocketClient;
import okhttp3.Response;
import okio.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneBotClient {
    private final WebSocketClient api;
    private final WebSocketClient event;
    private final String url;
    public OneBotClient(String url) {
        this.url = url;
        api = new WebSocketClient();
        event = new WebSocketClient();
    }

    public void connect() {
        api.connect(url + "/api", new APICallback());
        event.connect(url + "/event", new EventCallback());
    }


    public void send(String msg) {
        api.sendMessage(msg);
    }
}

class APICallback implements WebSocketClient.WebSocketCallback {
    private static final Logger logger = LoggerFactory.getLogger("API");
    @Override
    public void onOpen(Response response) {
        logger.info("API Connected.");
    }

    @Override
    public void onMessage(String text) {
    }

    @Override
    public void onBinaryMessage(ByteString bytes) {

    }

    @Override
    public void onClosing(int code, String reason) {

    }

    @Override
    public void onClosed(int code, String reason) {

    }

    @Override
    public void onFailure(Throwable t, Response response) {

    }
}

class EventCallback implements WebSocketClient.WebSocketCallback {
    private static final Logger logger = LoggerFactory.getLogger("Event");
    @Override
    public void onOpen(Response response) {
        logger.info("Event Connected.");
    }

    @Override
    public void onMessage(String text) {
        logger.info("Received: {}", text);
        new EventHandler(text);
    }

    @Override
    public void onBinaryMessage(ByteString bytes) {

    }

    @Override
    public void onClosing(int code, String reason) {

    }

    @Override
    public void onClosed(int code, String reason) {

    }

    @Override
    public void onFailure(Throwable t, Response response) {

    }
}