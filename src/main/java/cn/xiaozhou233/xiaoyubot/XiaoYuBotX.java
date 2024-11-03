package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.network.WebSocketClient;
import cn.xiaozhou233.xiaoyubot.utils.Configuration;
import cn.xiaozhou233.xiaoyubot.utils.PluginManager;

public class XiaoYuBotX {
    public static void main(String[] args) {
        // 连接 OneBot
        System.out.println("[INFO] XiaoYuBotX is starting...");

        System.out.println("[INFO] Loading configuration...");
        Configuration configuration = new Configuration();
        String wsUrl = configuration.getConfigNode().get("wsUrl").asText();

        WebSocketClient webSocketClient = new WebSocketClient();
        webSocketClient.connect(wsUrl);

        // 加载插件
        System.out.println("[INFO] Loading plugins...");
        PluginManager.loadPlugins();
        System.out.println("[INFO] XiaoYuBotX is ready!");
    }
}