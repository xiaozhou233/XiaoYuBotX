package cn.xiaozhou233.xiaoyubot.plugins;

import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import cn.xiaozhou233.xiaoyubot.config.ConfigManager;

public class BotInstance {
    private final Plugin plugin;

    public BotInstance(Plugin plugin) {
        this.plugin = plugin;
    }

    public ConfigManager getConfig() {
        return PluginManager.getConfig(plugin);
    }

    public PluginManager getPluginManager() {
        return XiaoYuBotX.getPluginManager();
    }

}
