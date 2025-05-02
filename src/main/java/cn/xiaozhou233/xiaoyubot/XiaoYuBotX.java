package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.Network.Websocket;
import cn.xiaozhou233.xiaoyubot.OneBot.Client.Client;

public class XiaoYuBotX {
    public static void main(String[] args) {
        new Client("ws://localhost:3001", "").connect();
    }
}
