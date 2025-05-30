package cn.xiaozhou233.xiaoyubot.OneBot.Client;

import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import onebot11.event.message.*;
import onebot11.event.request.*;
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
                    Message msg = mapper.readValue(message, Message.class);
                    if (msg.getSubType().equals("group")) {
                        GroupMessage groupMsg = mapper.readValue(message, GroupMessage.class);
                        XiaoYuBotX.getEventBus().post(groupMsg);
                    } else if (msg.getSubType().equals("private")) {
                        PrivateMessage privateMsg = mapper.readValue(message, PrivateMessage.class);
                        XiaoYuBotX.getEventBus().post(privateMsg);
                    }
                    break;
                case "notice":
                    //TODO: Handle notice event
                    break;
                case "request":
                    Request request = mapper.readValue(message, Request.class);
                    if (request.getRequestType().equals("friend")) {
                        FriendRequest friendRequest = mapper.readValue(message, FriendRequest.class);
                        XiaoYuBotX.getEventBus().post(friendRequest);
                    } else if (request.getRequestType().equals("group")) {
                        GroupRequest groupRequest = mapper.readValue(message, GroupRequest.class);
                        XiaoYuBotX.getEventBus().post(groupRequest);
                    }
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
