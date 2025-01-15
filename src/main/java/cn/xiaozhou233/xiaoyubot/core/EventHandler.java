package cn.xiaozhou233.xiaoyubot.core;

import cn.xiaozhou233.xiaoyubot.onebot.event.message.*;
import cn.xiaozhou233.xiaoyubot.onebot.event.notice.*;
import cn.xiaozhou233.xiaoyubot.onebot.event.request.*;

import cn.xiaozhou233.xiaoyubot.plugins.PluginManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventHandler {
    private static final Logger logger = LoggerFactory.getLogger("EventHandler");
    private static final ObjectMapper mapper = new ObjectMapper();
    public EventHandler(String eventMsg){
        try {
            JsonNode event = mapper.readTree(eventMsg);
            switch (event.get("post_type").asText()){
                case "message":
                    new MessageHandler().handle(event);
                    break;
                case "notice":
                    new NoticeHandler().handle(event);
                    break;
                case "request":
                    new RequestHandler().handle(event);
                    break;
                case "meta_event":
                    // Ignore meta event
                    break;
                default:
                    logger.warn("Unknown event type: {}", event.get("post_type").asText());
                    break;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

class MessageHandler {
    private static final ObjectMapper mapper = new ObjectMapper();
    public void handle(JsonNode event) throws JsonProcessingException {
        if(event.get("message_type").asText().equals("group")){
            GroupMessage groupMessage = mapper.readValue(event.toString(), GroupMessage.class);
            PluginManager.getPlugins().forEach(plugin -> plugin.onGroupMessage(groupMessage));
        } else if(event.get("message_type").asText().equals("private")){
            PrivateMessage privateMessage = mapper.readValue(event.toString(), PrivateMessage.class);
            PluginManager.getPlugins().forEach(plugin -> plugin.onPrivateMessage(privateMessage));
        } else {
            throw new RuntimeException("Unknown message type: " + event.get("message_type").asText());
        }
    }
}

class NoticeHandler {
    private static final ObjectMapper mapper = new ObjectMapper();
    public void handle(JsonNode event) throws JsonProcessingException {
        String noticeType = event.get("notice_type").asText();
        switch (noticeType) {
            case "group_upload":
                GroupUploadNotice groupUploadNotice = mapper.readValue(event.toString(), GroupUploadNotice.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupUploadNotice(groupUploadNotice));
                break;
            case "group_admin":
                GroupAdminNotice groupAdminNotice = mapper.readValue(event.toString(), GroupAdminNotice.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupAdminNotice(groupAdminNotice));
                break;
            case "group_decrease":
                GroupDecreaseNotice groupDecreaseNotice = mapper.readValue(event.toString(), GroupDecreaseNotice.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupDecreaseNotice(groupDecreaseNotice));
                break;
            case "group_increase":
                GroupIncreaseNotice groupIncreaseNotice = mapper.readValue(event.toString(), GroupIncreaseNotice.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupIncreaseNotice(groupIncreaseNotice));
                break;
            case "group_ban":
                GroupBanNotice groupBanNotice = mapper.readValue(event.toString(), GroupBanNotice.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupBanNotice(groupBanNotice));
                break;
            case "friend_add":
                FriendAddNotice friendAddNotice = mapper.readValue(event.toString(), FriendAddNotice.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onFriendAddNotice(friendAddNotice));
                break;
            case "group_recall":
                GroupRecallNotice groupRecallNotice = mapper.readValue(event.toString(), GroupRecallNotice.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupRecallNotice(groupRecallNotice));
                break;
            case "friend_recall":
                FriendRecallNotice friendRecallNotice = mapper.readValue(event.toString(), FriendRecallNotice.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onFriendRecallNotice(friendRecallNotice));
                break;
            case "notify":
                /*
                 * same notice_type: poke, luckking, honor
                 */
                String subType = event.get("sub_type").asText();
                switch (subType) {
                    case "poke" -> {
                        GroupPokeNotice pokeNotice = mapper.readValue(event.toString(), GroupPokeNotice.class);
                        PluginManager.getPlugins().forEach(plugin -> plugin.onGroupPokeNotice(pokeNotice));
                    }
                    case "lucky_king" -> {
                        GroupLuckyKingNotice luckyKingNotice = mapper.readValue(event.toString(), GroupLuckyKingNotice.class);
                        PluginManager.getPlugins().forEach(plugin -> plugin.onGroupLuckKingNotice(luckyKingNotice));
                    }
                    case "honor" -> {
                        GroupHonorChangeNotice honorNotice = mapper.readValue(event.toString(), GroupHonorChangeNotice.class);
                        PluginManager.getPlugins().forEach(plugin -> plugin.onGroupHonorChangeNotice(honorNotice));
                    }
                }
                break;
            default:
                break;
        }
    }
}

class RequestHandler {
    private static final ObjectMapper mapper = new ObjectMapper();
    public void handle(JsonNode event) throws JsonProcessingException {
        String requestType = event.get("request_type").asText();
        switch (requestType) {
            case "friend":
                FriendAddRequest request = mapper.readValue(event.toString(), FriendAddRequest.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onFriendRequest(request));
                break;
            case "group":
                GroupAddRequest groupRequest = mapper.readValue(event.toString(), GroupAddRequest.class);
                PluginManager.getPlugins().forEach(plugin -> plugin.onGroupRequest(groupRequest));
                break;
            default:
                break;
        }
    }
}
