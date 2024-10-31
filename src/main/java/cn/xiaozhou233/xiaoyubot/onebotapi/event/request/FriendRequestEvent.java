package cn.xiaozhou233.xiaoyubot.onebotapi.event.request;

import com.fasterxml.jackson.databind.JsonNode;

// 加好友请求事件类
public class FriendRequestEvent extends RequestEvent {
    public FriendRequestEvent(JsonNode jsonNode) {
        super(jsonNode);
    }
}
