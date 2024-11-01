package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.network.WebSocketClient;
import cn.xiaozhou233.xiaoyubot.utils.PluginManager;

public class XiaoYuBotX {
    public static void main(String[] args) {
        // 连接 OneBot
        System.out.println("[INFO] XiaoYuBotX is starting...");
        System.out.println("[INFO] Connecting to OneBot...");
        WebSocketClient webSocketClient = new WebSocketClient();
        webSocketClient.connect("ws://127.0.0.1:3001");

        // 加载插件
        System.out.println("[INFO] Loading plugins...");
        PluginManager.loadPlugins();
        System.out.println("[INFO] XiaoYuBotX is ready!");
    }
}