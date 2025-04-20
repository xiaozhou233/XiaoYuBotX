package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.Network.Websocket;

public class XiaoYuBotX {
    public static void main(String[] args) {
        new Websocket("ws://localhost:3001").connect();
    }
}
