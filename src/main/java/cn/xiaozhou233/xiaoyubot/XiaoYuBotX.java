package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.Config.Config;
import cn.xiaozhou233.xiaoyubot.Event.EventBus;
import cn.xiaozhou233.xiaoyubot.Network.Websocket;
import cn.xiaozhou233.xiaoyubot.OneBot.Client.Client;
import cn.xiaozhou233.xiaoyubot.Plugin.PluginManager;

import java.io.File;

public class XiaoYuBotX {
    public static EventBus eventBus = new EventBus();
    public static PluginManager pluginManager = new PluginManager();
    private Config config;

    public XiaoYuBotX(){
        // Config System
        config = new Config(new File("./config.json"));
        // Plugin System
        pluginManager.loadPlugins();

        // Connect to Onebot
        new Client(config.get("onebot").get("ws").asText(),
                config.get("onebot").get("http").asText()).connect();
    }

    public static EventBus getEventBus() {
        return eventBus;
    }
}
