package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.network.WebSocketClient;
import cn.xiaozhou233.xiaoyubot.utils.Configuration;
import cn.xiaozhou233.xiaoyubot.utils.PluginManager;
import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

public class XiaoYuBotX {
    public static String httpUrl;
    private static final TaggedLogger logger = Logger.tag("Main");
    public static void main(String[] args) {
        logger.info("Starting...");

        // 加载配置
        logger.info("Loading configuration...");
        Configuration configuration = new Configuration();
        String wsUrl = configuration.getConfigNode().get("wsUrl").asText();
        httpUrl = configuration.getConfigNode().get("httpUrl").asText();

        // 连接 WebSocket
        logger.info("Connecting to WebSocket...");
        WebSocketClient webSocketClient = new WebSocketClient();
        webSocketClient.connect(wsUrl);

        // 加载插件
        logger.info("Loading plugins...");
        PluginManager.loadPlugins();
        logger.info("XiaoYuBotX is Ready!");
    }
}