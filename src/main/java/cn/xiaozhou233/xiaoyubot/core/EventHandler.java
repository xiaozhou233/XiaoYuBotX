package cn.xiaozhou233.xiaoyubot.core;

import cn.xiaozhou233.xiaoyubot.onebot.event.message.GroupMessage;
import cn.xiaozhou233.xiaoyubot.onebot.event.message.PrivateMessage;
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
    public void handle(JsonNode event) {

    }
}

class RequestHandler {
    public void handle(JsonNode event) {

    }
}