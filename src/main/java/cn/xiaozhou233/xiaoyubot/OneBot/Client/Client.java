package cn.xiaozhou233.xiaoyubot.OneBot.Client;

import cn.xiaozhou233.xiaoyubot.Network.Websocket;

public class Client {
    private String ws;
    private String http;
    private final Websocket websocket;
    public Client(String ws, String http){
        this.ws = ws;
        this.http = http;
        this.websocket = new Websocket(ws);
    }

    public void connect() {
        this.websocket.connect();
    }
    
}
