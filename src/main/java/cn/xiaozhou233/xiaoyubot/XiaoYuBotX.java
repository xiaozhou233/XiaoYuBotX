package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.Event.EventBus;
import cn.xiaozhou233.xiaoyubot.Network.Websocket;
import cn.xiaozhou233.xiaoyubot.OneBot.Client.Client;

public class XiaoYuBotX {
    public static EventBus eventBus = new EventBus();
    public static void main(String[] args) {
        new Client("ws://localhost:3001", "").connect();
    }

    public static EventBus getEventBus() {
        return eventBus;
    }
}
