package cn.xiaozhou233.xiaoyubot.OneBot.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import onebot11.event.Event;

public class EventListener {
    private static final Logger logger = LoggerFactory.getLogger("EventListener");
    private static final ObjectMapper mapper = new ObjectMapper();
    public static void onMessage(String message) {
        try {
            Event event = mapper.readValue(message, Event.class);
            switch (event.getPostType()) {
                case "message":
                    //TODO: Handle message event
                    break;
                case "notice":
                    //TODO: Handle notice event
                    break;
                case "request":
                    //TODO: Handle request event
                    break;
                case "meta_event":
                    break;
                default:
                    logger.warn("Unknown event type: {}", event.getPostType());
            }
        } catch (JsonProcessingException e) {
            logger.error("Error while parsing json: {}", message, e.getCause());
        }
    }
}
