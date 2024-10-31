package cn.xiaozhou233.xiaoyubot.utils;


import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.GroupMessageEvent;
import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.PrivateMessageEvent;
import com.fasterxml.jackson.databind.JsonNode;

public class MessageHandle {
    public static void handle(JsonNode message){
        System.out.println("[DEBUG] Handling message: " + message.toString());
        // Check event type.
        switch (message.get("post_type").asText()){
            case "message":
                // check message type.
                if(message.get("message_type").asText().equals("private")){
                    PrivateMessageEvent privateMessage = new PrivateMessageEvent(message);
                    System.out.println("[DEBUG] Received private message: " + privateMessage.toString());
                    // TODO: Handle private message.
                } else if (message.get("message_type").asText().equals("group")){
                    GroupMessageEvent groupMessage = new GroupMessageEvent(message);
                    System.out.println("[DEBUG] Received group message: " + groupMessage.toString());
                    // TODO: Handle group message.
                }else {
                    System.out.println("[ERROR] Unknown message type: " + message.get("message_type").asText());
                }
                break;
            case "notice":
                // TODO: Handle notice.
                break;
            case "request":
                // TODO: Handle request.
                break;
            case "meta_event":
                // TODO: Handle meta event.
                break;

            default:
                System.out.println("[WARN] Unknown event type: " + message.get("post_type").asText());
        }
    }
}
