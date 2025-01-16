package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.core.OneBotClient;
import cn.xiaozhou233.xiaoyubot.onebot.api.send_group_msg;
import cn.xiaozhou233.xiaoyubot.plugins.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XiaoYuBotX {
    private static final Logger logger = LoggerFactory.getLogger("XiaoYuBotX");
    private static final String wsUrl = "ws://192.168.2.222:3001";
    private static final String httpUrl = "http://192.168.2.222:3000";
    private static OneBotClient oneBotClient;
    private static PluginManager pluginManager;

    public static void main(String[] args){
        // setting default uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) ->
                logger.error("Unhandled exception in thread {}", thread.getName(), throwable));

        logger.info("Starting...");

        logger.info("Connecting to OneBot...");
        oneBotClient = new OneBotClient(wsUrl, httpUrl);
        oneBotClient.connect();

        logger.info("Loading plugins...");
        pluginManager = new PluginManager();
        pluginManager.loadPlugins();

        logger.info("XiaoYuBotX started!");
    }


    public static OneBotClient getOneBotClient() {
        return oneBotClient;
    }
}
