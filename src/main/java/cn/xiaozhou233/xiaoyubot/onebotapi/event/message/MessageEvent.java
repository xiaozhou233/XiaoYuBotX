package cn.xiaozhou233.xiaoyubot.onebotapi.event.message;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class MessageEvent {
    protected long time;
    protected long selfId;
    protected String postType;
    protected String messageType;
    protected int messageId;
    protected long userId;
    protected String message;
    protected String rawMessage;
    protected int font;

    public MessageEvent(JsonNode jsonNode) {
        this.time = jsonNode.get("time").asLong();
        this.selfId = jsonNode.get("self_id").asLong();
        this.postType = jsonNode.get("post_type").asText();
        this.messageType = jsonNode.get("message_type").asText();
        this.messageId = jsonNode.get("message_id").asInt();
        this.userId = jsonNode.get("user_id").asLong();
        this.message = jsonNode.get("message").asText();
        this.rawMessage = jsonNode.get("raw_message").asText();
        this.font = jsonNode.get("font").asInt();
    }
}

