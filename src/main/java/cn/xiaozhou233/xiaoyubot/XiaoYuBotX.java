package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.network.WebSocketClient;
import cn.xiaozhou233.xiaoyubot.utils.Configuration;
import cn.xiaozhou233.xiaoyubot.utils.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XiaoYuBotX {
    public static String httpUrl;
    public static String wsUrl;
    public static Configuration configuration;
    public static WebSocketClient webSocketClient;
    private static final Logger logger = LoggerFactory.getLogger("XiaoYuBotX");

    public static void main(String[] args) {
        // setting default uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) ->
                logger.error("Unhandled exception in thread {}", thread.getName(), throwable));

        logger.info("Starting...");

        logger.info("Loading configuration...");
        configuration = new Configuration();
        wsUrl = configuration.getConfigNode().get("wsUrl").asText();
        httpUrl = configuration.getConfigNode().get("httpUrl").asText();

        logger.info("Connecting to WebSocket server...");
        webSocketClient = new WebSocketClient(wsUrl);
        webSocketClient.connect();

        logger.info("Loading plugins...");
        PluginManager.loadPlugins();
        logger.info("XiaoYuBotX is ready!");
    }
}