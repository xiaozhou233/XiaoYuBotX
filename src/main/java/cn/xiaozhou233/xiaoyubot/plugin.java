package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.configuration.ConfigManager;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.meta.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.notice.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.request.*;
import org.slf4j.Logger;

public abstract class plugin {
    private ConfigManager configManager;
    private Logger logger;

    public abstract void onEnable();

    public abstract void onDisable();

    // 消息事件处理
    public void onPrivateMessage(PrivateMessageEvent messageEvent) {
    }

    public void onGroupMessage(GroupMessageEvent messageEvent) {
    }

    public void onAtBot(GroupMessageEvent messageEvent) {
    }

    // 元事件处理
    public void onHeartbeat(HeartbeatEvent heartbeatEvent) {
    }

    public void onLifecycle(LifecycleEvent lifecycleEvent) {

    }

    // 通知事件处理
    public void onFriendAdd(FriendAddNotice friendAddNotice) {
    }

    public void onFriendRecall(FriendRecallNotice friendRecallNotice) {
    }

    public void onGroupAdmin(GroupAdminNotice groupAdminNotice) {
    }

    public void onGroupBan(GroupBanNotice groupBanNotice) {
    }

    public void onGroupDecrease(GroupDecreaseNotice groupDecreaseNotice) {
    }

    public void onGroupHonorChange(GroupHonorChangeNotice groupHonorChangeNotice) {
    }

    public void onGroupIncrease(GroupIncreaseNotice groupIncreaseNotice) {
    }

    public void onGroupLuckyKing(GroupLuckyKingNotice groupLuckyKingNotice) {
    }

    public void onGroupPoke(GroupPokeNotice groupPokeNotice) {
    }

    public void onGroupRecall(GroupRecallNotice groupRecallNotice) {
    }

    public void onGroupUpload(GroupUploadNotice groupUploadNotice) {
    }

    // 请求事件处理
    public void onFriendRequest(FriendRequestEvent friendRequestEvent) {
    }

    public void onGroupRequest(GroupRequestEvent groupRequestEvent) {
    }

    // 筱雨框架事件
    public void onTriggerKeyword(String keyword, GroupMessageEvent messageEvent) {
    }

    // 筱雨框架

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public ConfigManager getConfig() {
        return configManager;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }
}
