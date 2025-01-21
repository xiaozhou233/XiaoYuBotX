package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.command.CommandParser;
import cn.xiaozhou233.xiaoyubot.core.OneBotClient;
import cn.xiaozhou233.xiaoyubot.plugins.PluginManager;
import cn.xiaozhou233.xiaoyubot.utils.BotConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XiaoYuBotX {
    private static final Logger logger = LoggerFactory.getLogger("XiaoYuBotX");
    private static OneBotClient oneBotClient;
    private static PluginManager pluginManager;
    private static BotConfig botConfig;

    public static void main(String[] args){
        // setting default uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) ->
                logger.error("An exception occurred in thread {}", thread.getName(), throwable));

        logger.info("Starting...");

        logger.info("Loading Bot Config...");
        String httpUrl;
        String wsUrl;
        try {
            botConfig = new BotConfig();
            wsUrl = botConfig.get("wsUrl").asText();
            httpUrl = botConfig.get("httpUrl").asText();
        } catch (Exception e) {
            logger.error("Cannot init BotConfig! Exit...");
            return;
        }

        logger.info("Connecting to OneBot...");
        oneBotClient = new OneBotClient(wsUrl, httpUrl);
        oneBotClient.connect();

        logger.info("Loading plugins...");
        pluginManager = new PluginManager();
        pluginManager.loadPlugins();

        logger.info("Init Command Parser...");
        new CommandParser();


        logger.info("XiaoYuBotX started!");
    }


    public static OneBotClient getOneBotClient() {
        return oneBotClient;
    }

    public static BotConfig getBotConfig() {
        return botConfig;
    }

    public static PluginManager getPluginManager() {
        return pluginManager;
    }
}
