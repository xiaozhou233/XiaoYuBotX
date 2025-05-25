package cn.xiaozhou233.xiaoyubot.Plugin;

import cn.xiaozhou233.xiaoyubot.Event.EventBus;

public abstract class JavaPlugin {
    protected PluginInfo pluginInfo;
    protected EventBus eventBus;

    public void init(PluginInfo info, EventBus eventBus){
        this.pluginInfo = info;
        this.eventBus = eventBus;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    abstract public void onEnable();

    abstract public void onDisable();
}
