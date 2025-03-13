package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.config.Config;
import cn.xiaozhou233.xiaoyubot.network.WebSocketClient;
import cn.xiaozhou233.xiaoyubot.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class XiaoYuBotX {
    private static final Logger logger = LoggerFactory.getLogger("XiaoYuBotX");
    private static Config config = new Config("config.json");
    private static final String ws = config.getConfig().get("onebot").get("ws").asText();
    private static WebSocketClient client = new WebSocketClient(ws);

    public XiaoYuBotX() {
        // Start Websocket Client
        client.start();
        // wait connected // client.isConnected()
        while (!client.isConnected()) {
            Utils.sleep(3000);
        }
    }


}

