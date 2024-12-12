package cn.xiaozhou233.ExamplePlugin;

import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.GroupMessageEvent;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.PrivateMessageEvent;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.meta.LifecycleEvent;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.notice.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.request.FriendRequestEvent;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.request.GroupRequestEvent;
import cn.xiaozhou233.xiaoyubot.plugin;

import java.util.Objects;

public class PluginMain extends plugin {
    public void onEnable() {
        System.out.println("EchoPlugin is enabled!");
    }

    public void onDisable() {
        System.out.println("EchoPlugin is disabled!");
    }

    public void onPrivateMessage(PrivateMessageEvent messageEvent) {
        System.out.printf("[Private] %s(%s) > %s \n",
                messageEvent.getSender().getNickname(),
                messageEvent.getSender().getUserId(),
                messageEvent.getRawMessage());
    }

    public void onGroupMessage(GroupMessageEvent messageEvent) {
    System.out.printf("[Group] <%s> [%s] %s(%s) > %s \n",
            messageEvent.getSender().getTitle().isEmpty() ?
                    messageEvent.getSender().getLevel() : messageEvent.getSender().getTitle() + " - " + messageEvent.getSender().getLevel(),
            messageEvent.getSender().getRole(),
            messageEvent.getSender().getNickname(),
            messageEvent.getSender().getUserId(),
            messageEvent.getRawMessage());
    }


    public void onAtBot(GroupMessageEvent messageEvent) {
        System.out.printf("[@Bot] <%s> [%s] %s(%s) > %s \n",
                messageEvent.getSender().getTitle().isEmpty() ?
                        messageEvent.getSender().getLevel() : messageEvent.getSender().getTitle() + " - " + messageEvent.getSender().getLevel(),
                messageEvent.getSender().getRole(),
                messageEvent.getSender().getNickname(),
                messageEvent.getSender().getUserId(),
                messageEvent.getRawMessage());
    }


    public void onFriendAdd(FriendAddNotice friendAddNotice) {
        System.out.printf("[Notice] [FriendAdd] %s added as friend\n",
                friendAddNotice.getUserId());
    }


    public void onGroupRequest(GroupRequestEvent groupRequestEvent) {
        System.out.printf("[Request] [GroupRequest] %s requested to join Group %s (Comment: %s)\n",
                groupRequestEvent.getUserId(),
                groupRequestEvent.getGroupId(),
                groupRequestEvent.getComment());
    }


    public void onFriendRequest(FriendRequestEvent friendRequestEvent) {
        System.out.printf("[Request] [FriendRequest] %s requested to be friend (Comment: %s)\n",
                friendRequestEvent.getUserId(),
                friendRequestEvent.getComment());
    }


    public void onGroupBan(GroupBanNotice groupBanNotice) {
        System.out.printf("[Notice] [GroupBan] %s banned %s for %s seconds in Group %s \n",
                groupBanNotice.getOperatorId(),
                groupBanNotice.getUserId(),
                groupBanNotice.getDuration(),
                groupBanNotice.getGroupId());
    }


    public void onGroupIncrease(GroupIncreaseNotice groupIncreaseNotice) {
        System.out.printf("[Notice] [GroupIncrease] %s joined Group %s (Operator: %s)\n",
                groupIncreaseNotice.getUserId(),
                groupIncreaseNotice.getGroupId(),
                groupIncreaseNotice.getOperatorId());
    }


    public void onGroupDecrease(GroupDecreaseNotice groupDecreaseNotice) {
        System.out.printf("[Notice] [GroupDecrease] %s left Group %s (Operator: %s)\n",
                groupDecreaseNotice.getUserId(),
                groupDecreaseNotice.getGroupId(),
                groupDecreaseNotice.getOperatorId());
    }


    public void onGroupPoke(GroupPokeNotice groupPokeNotice) {
        System.out.printf("[Notice] [GroupPoke] %s poked %s in Group %s\n",
                groupPokeNotice.getUserId(),
                groupPokeNotice.getTargetId(),
                groupPokeNotice.getGroupId());
    }



    public void onLifecycle(LifecycleEvent lifecycleEvent) {
        if (Objects.equals(lifecycleEvent.getSubType(), "connect")){
            System.out.printf("[INFO] XiaoYuBotX connected to OneBot (UID:%s)",
                    lifecycleEvent.getSelfId());
        }
    }
}