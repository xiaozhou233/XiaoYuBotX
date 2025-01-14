package cn.xiaozhou233.xiaoyubot.plugins;

public interface Plugin {
    void onEnable();
    void onDisable();
    default void onGroupMessage(){

    }
}
