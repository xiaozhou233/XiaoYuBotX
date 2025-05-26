package cn.xiaozhou233.xiaoyubot.Plugin;

import cn.xiaozhou233.xiaoyubot.Config.Config;
import cn.xiaozhou233.xiaoyubot.Event.EventBus;
import org.slf4j.Logger;

public abstract class JavaPlugin {
    protected PluginInfo pluginInfo;
    protected EventBus eventBus;
    protected Logger logger;
    protected Config config;

    public void init(PluginInfo info, EventBus eventBus, Logger logger, Config config){
        this.pluginInfo = info;
        this.eventBus = eventBus;
        this.logger = logger;
        this.config = config;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Logger getLogger() {
        return logger;
    }

    public Config getConfig() {
        return config;
    }

    abstract public void onEnable();

    abstract public void onDisable();
}
