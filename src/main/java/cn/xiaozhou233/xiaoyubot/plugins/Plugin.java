package cn.xiaozhou233.xiaoyubot.plugins;

import cn.xiaozhou233.xiaoyubot.onebot.event.message.GroupMessage;
import cn.xiaozhou233.xiaoyubot.onebot.event.message.PrivateMessage;

public abstract class Plugin {
    public abstract void onEnable();
    public abstract void onDisable();

    // Message
    public void onGroupMessage(GroupMessage event) {}
    public void onPrivateMessage(PrivateMessage event) {}

}
