package cn.xiaozhou233.xiaoyubot.plugins;

import cn.xiaozhou233.xiaoyubot.onebot.event.message.*;
import cn.xiaozhou233.xiaoyubot.onebot.event.notice.*;
import cn.xiaozhou233.xiaoyubot.onebot.event.request.*;

public abstract class Plugin {
    public abstract void onEnable();

    public abstract void onDisable();

    // Message
    public void onGroupMessage(GroupMessage event) {}

    public void onPrivateMessage(PrivateMessage event) {}

    // Notice
    public void onGroupUploadNotice(GroupUploadNotice event) {}

    public void onGroupAdminNotice(GroupAdminNotice event) {}

    public void onGroupDecreaseNotice(GroupDecreaseNotice event) {}

    public void onGroupIncreaseNotice(GroupIncreaseNotice event) {}

    public void onGroupBanNotice(GroupBanNotice event) {}

    public void onFriendAddNotice(FriendAddNotice event) {}

    public void onGroupRecallNotice(GroupRecallNotice event) {}

    public void onFriendRecallNotice(FriendRecallNotice event) {}

    public void onGroupPokeNotice(GroupPokeNotice event) {}

    public void onGroupLuckKingNotice(GroupLuckyKingNotice event) {}

    public void onGroupHonorChangeNotice(GroupHonorChangeNotice event) {}

    // Request
    public void onFriendRequest(FriendAddRequest event) {}

    public void onGroupRequest(GroupAddRequest event) {}
}

