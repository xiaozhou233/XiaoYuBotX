package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.meta.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.notice.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.request.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.message.MessageSegment;

public interface plugin {
    // 插件加载与卸载
    void onEnable();
    void onDisable();

    // 消息事件处理
    default void onPrivateMessage(PrivateMessageEvent messageEvent) {}
    default void onGroupMessage(GroupMessageEvent messageEvent) {}
    default void onAtBot(GroupMessageEvent messageEvent) {}

    // 元事件处理
    default void onHeartbeat(HeartbeatEvent heartbeatEvent) {}
    default void onLifecycle(LifecycleEvent lifecycleEvent) {}

    // 通知事件处理
    default void onFriendAdd(FriendAddNotice friendAddNotice) {}
    default void onFriendRecall(FriendRecallNotice friendRecallNotice) {}
    default void onGroupAdmin(GroupAdminNotice groupAdminNotice) {}
    default void onGroupBan(GroupBanNotice groupBanNotice) {}
    default void onGroupDecrease(GroupDecreaseNotice groupDecreaseNotice) {}
    default void onGroupHonorChange(GroupHonorChangeNotice groupHonorChangeNotice) {}
    default void onGroupIncrease(GroupIncreaseNotice groupIncreaseNotice) {}
    default void onGroupLuckyKing(GroupLuckyKingNotice groupLuckyKingNotice) {}
    default void onGroupPoke(GroupPokeNotice groupPokeNotice) {}
    default void onGroupRecall(GroupRecallNotice groupRecallNotice) {}
    default void onGroupUpload(GroupUploadNotice groupUploadNotice) {}

    // 请求事件处理
    default void onFriendRequest(FriendRequestEvent friendRequestEvent) {}
    default void onGroupRequest(GroupRequestEvent groupRequestEvent) {}

    // 筱雨框架事件
    default void onTriggerKeyword(String keyword, GroupMessageEvent messageEvent) {}
}
