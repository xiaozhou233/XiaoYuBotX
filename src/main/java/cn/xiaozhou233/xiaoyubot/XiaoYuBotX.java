package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.config.Config;
import cn.xiaozhou233.xiaoyubot.network.WebSocketClient;
import cn.xiaozhou233.xiaoyubot.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class XiaoYuBotX {
    private static final Logger logger = LoggerFactory.getLogger("XiaoYuBotX");
    private static Config config;
    private static WebSocketClient client;

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            logger.error("Exception in Thread {}: ", t.getName(), e);
        });

        Utils.check(logger);

        config = new Config("config.json");
        client = new WebSocketClient(config.getConfig().get("onebot").get("ws").asText());
        client.start();

    }


}

