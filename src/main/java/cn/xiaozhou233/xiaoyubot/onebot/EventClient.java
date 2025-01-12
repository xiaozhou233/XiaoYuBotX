package cn.xiaozhou233.xiaoyubot.onebot;

import cn.xiaozhou233.xiaoyubot.network.WSListener;
import cn.xiaozhou233.xiaoyubot.network.WebsocketClient;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.WebSocket;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventClient{
    private static final Logger logger = LoggerFactory.getLogger("Event");

    public EventClient(String url) {
        url = url + "/event";
        new WebsocketClient(url, new Listener(new OkHttpClient(), url));
    }

    private static class Listener extends WSListener {
        public Listener(OkHttpClient client, String url) {
            super(client, url);
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            logger.info("Connected to Event server! [{}]", response.request().url());
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            logger.info("Received message: {}", text);
        }
    }
}
