package cn.xiaozhou233.xiaoyubot.utils;

import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.meta.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.notice.*;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.request.*;

import com.fasterxml.jackson.databind.JsonNode;

public class MessageHandle {

    public static void handle(JsonNode message) {

        String postType = message.path("post_type").asText("unknown");

        switch (postType) {
            case "message":
                handleMessage(message);
                break;
            case "notice":
                handleNotice(message);
                break;
            case "request":
                handleRequest(message);
                break;
            case "meta_event":
                handleMetaEvent(message);
                break;
            default:
                System.out.println("[WARN] [MessageHandle] Unknown event type: " + postType);
                break;
        }
    }

    private static void handleMessage(JsonNode message) {
        String messageType = message.path("message_type").asText();

        switch (messageType) {
            case "private":
                PrivateMessageEvent privateMessage = new PrivateMessageEvent(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onPrivateMessage(privateMessage));
                break;
            case "group":
                GroupMessageEvent groupMessage = new GroupMessageEvent(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupMessage(groupMessage));
                // At机器人事件
                for (var segment : groupMessage.getMessageSegments()) {
                    JsonNode qqNode = segment.getData().get("qq");
                    boolean isAt = segment.getType().equals("at");
                    boolean isSelf = qqNode != null && groupMessage.getSelfId() == qqNode.asLong();

                    if (isAt && isSelf) {
                        PluginManager.getPlugins().forEach(plugin -> plugin.onAtBot(groupMessage));
                        // for keyword bind manager
                        KeywordBind.onAtBotEvent(groupMessage);
                        break;
                    }
                }

                break;
            default:
                System.out.println("[WARN] [MessageHandle] Unknown message type: " + messageType);
                break;
        }
    }

    private static void handleMetaEvent(JsonNode message) {
        String metaEventType = message.path("meta_event_type").asText();

        switch (metaEventType) {
            case "heartbeat":
                HeartbeatEvent heartbeatEvent = new HeartbeatEvent(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onHeartbeat(heartbeatEvent));
                break;
            case "lifecycle":
                LifecycleEvent lifecycleEvent = new LifecycleEvent(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onLifecycle(lifecycleEvent));
                break;
            default:
                System.out.println("[ERROR] Unknown meta event type: " + metaEventType);
                break;
        }
    }

    private static void handleNotice(JsonNode message) {
        String noticeType = message.path("notice_type").asText();

        switch (noticeType) {
            case "friend_add":
                FriendAddNotice friendAddNotice = new FriendAddNotice(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onFriendAdd(friendAddNotice));
                break;
            case "friend_recall":
                FriendRecallNotice friendRecallNotice = new FriendRecallNotice(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onFriendRecall(friendRecallNotice));
                break;
            case "group_admin":
                GroupAdminNotice groupAdminNotice = new GroupAdminNotice(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupAdmin(groupAdminNotice));
                break;
            case "group_ban":
                GroupBanNotice groupBanNotice = new GroupBanNotice(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupBan(groupBanNotice));
                break;
            case "group_decrease":
                GroupDecreaseNotice groupDecreaseNotice = new GroupDecreaseNotice(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupDecrease(groupDecreaseNotice));
                break;
            case "group_increase":
                GroupIncreaseNotice groupIncreaseNotice = new GroupIncreaseNotice(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupIncrease(groupIncreaseNotice));
                break;
            case "group_recall":
                GroupRecallNotice groupRecallNotice = new GroupRecallNotice(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupRecall(groupRecallNotice));
                break;
            case "group_upload":
                GroupUploadNotice groupUploadNotice = new GroupUploadNotice(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupUpload(groupUploadNotice));
                break;
            case "notify":
                switch (message.path("sub_type").asText()) {
                    case "poke":
                        GroupPokeNotice groupPokeNotice = new GroupPokeNotice(message);
                        PluginManager.getPlugins().forEach(plugin -> plugin.onGroupPoke(groupPokeNotice));
                        break;
                    case "lucky_king":
                        GroupLuckyKingNotice groupLuckyKingNotice = new GroupLuckyKingNotice(message);
                        PluginManager.getPlugins().forEach(plugin -> plugin.onGroupLuckyKing(groupLuckyKingNotice));
                        break;
                    case "honor":
                        GroupHonorChangeNotice groupHonorChangeNotice = new GroupHonorChangeNotice(message);
                        PluginManager.getPlugins().forEach(plugin -> plugin.onGroupHonorChange(groupHonorChangeNotice));
                }
                break;
            default:
                System.out.println("[WARN] [MessageHandle] Unknown notice type: " + noticeType);
                break;
        }
    }

    private static void handleRequest(JsonNode message) {
        String requestType = message.path("request_type").asText();

        switch (requestType) {
            case "friend":
                FriendRequestEvent friendRequest = new FriendRequestEvent(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onFriendRequest(friendRequest));
                break;
            case "group":
                GroupRequestEvent groupRequest = new GroupRequestEvent(message);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupRequest(groupRequest));
                break;
            default:
                System.out.println("[WARN] [MessageHandle] Unknown request type: " + requestType);
                break;
        }
    }
}
