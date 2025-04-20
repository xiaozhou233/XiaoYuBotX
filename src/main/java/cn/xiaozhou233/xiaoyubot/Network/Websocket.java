package cn.xiaozhou233.xiaoyubot.Network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Websocket {
    private String url;
    private okhttp3.WebSocket ws;
    private final static Logger logger = LoggerFactory.getLogger("Websocket");
    private final OkHttpClient client;
    private final Listener listener;

    public Websocket(String url){
        this.url = url;
        this.client = new OkHttpClient.Builder()
                .pingInterval(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        this.listener = new Listener(this);
    }

    public void connect(){
        Request request = new Request.Builder()
                .url(url)
                .build();

        this.ws = client.newWebSocket(
            request,
            listener
        );
    }

    public void close(){
        this.ws.close(1000, "Server Closed");
    }
}