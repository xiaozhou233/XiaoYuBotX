package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.Event.EventBus;
import cn.xiaozhou233.xiaoyubot.Network.Websocket;
import cn.xiaozhou233.xiaoyubot.OneBot.Client.Client;
import cn.xiaozhou233.xiaoyubot.Plugin.PluginManager;

public class XiaoYuBotX {
    public static EventBus eventBus = new EventBus();
    public static PluginManager pluginManager = new PluginManager();

    public XiaoYuBotX(){
        // Plugin System
        pluginManager.loadPlugins();

        // Connect to Onebot
        new Client("ws://localhost:3001", "http://localhost:3000").connect();
    }

    public static EventBus getEventBus() {
        return eventBus;
    }
}
