package cn.xiaozhou233.xiaoyubot.plugins;

//import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.*;
//import cn.xiaozhou233.xiaoyubot.onebotapi.event.meta.*;
//import cn.xiaozhou233.xiaoyubot.onebotapi.event.notice.*;
//import cn.xiaozhou233.xiaoyubot.onebotapi.event.request.*;

public abstract class PluginInterface {

    public Bot Bot;

    public abstract void onEnable();
    public abstract void onDisable();
//
//    // Message Event
//    public void onPrivateMessage(PrivateMessageEvent messageEvent) {
//    }
//
//    public void onGroupMessage(GroupMessageEvent messageEvent) {
//    }
//
//    public void onAtBot(GroupMessageEvent messageEvent) {
//    }
//
//    // Meta Event
//    public void onHeartbeat(HeartbeatEvent heartbeatEvent) {
//    }
//
//    public void onLifecycle(LifecycleEvent lifecycleEvent) {
//
//    }
//
//    // Notice Event
//    public void onFriendAdd(FriendAddNotice friendAddNotice) {
//    }
//
//    public void onFriendRecall(FriendRecallNotice friendRecallNotice) {
//    }
//
//    public void onGroupAdmin(GroupAdminNotice groupAdminNotice) {
//    }
//
//    public void onGroupBan(GroupBanNotice groupBanNotice) {
//    }
//
//    public void onGroupDecrease(GroupDecreaseNotice groupDecreaseNotice) {
//    }
//
//    public void onGroupHonorChange(GroupHonorChangeNotice groupHonorChangeNotice) {
//    }
//
//    public void onGroupIncrease(GroupIncreaseNotice groupIncreaseNotice) {
//    }
//
//    public void onGroupLuckyKing(GroupLuckyKingNotice groupLuckyKingNotice) {
//    }
//
//    public void onGroupPoke(GroupPokeNotice groupPokeNotice) {
//    }
//
//    public void onGroupRecall(GroupRecallNotice groupRecallNotice) {
//    }
//
//    public void onGroupUpload(GroupUploadNotice groupUploadNotice) {
//    }
//
//    // Request Event
//    public void onFriendRequest(FriendRequestEvent friendRequestEvent) {
//    }
//
//    public void onGroupRequest(GroupRequestEvent groupRequestEvent) {
//    }

    public Bot getBot() {
        return this.Bot;
    }
}
