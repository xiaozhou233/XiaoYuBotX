package cn.xiaozhou233.xiaoyubot.plugins;

import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import org.slf4j.Logger;

public class BotInstance {
    private final Plugin plugin;

    public BotInstance(Plugin plugin) {
        this.plugin = plugin;
    }

    public PluginManager getPluginManager() {
        return XiaoYuBotX.getPluginManager();
    }

    public Logger getLogger() {
        return PluginManager.getLogger(plugin);
    }

}
