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
        this.time = jsonNode.path("time").asLong(0);                  // 默认值 0
        this.selfId = jsonNode.path("self_id").asLong(0);              // 默认值 0
        this.postType = jsonNode.path("post_type").asText("Unknown");  // 默认值 "Unknown"
        this.messageType = jsonNode.path("message_type").asText("Unknown"); // 默认值 "Unknown"
        this.messageId = jsonNode.path("message_id").asInt(0);         // 默认值 0
        this.userId = jsonNode.path("user_id").asLong(0);              // 默认值 0
        this.message = jsonNode.path("message").asText("");            // 默认值为空字符串
        this.rawMessage = jsonNode.path("raw_message").asText("");     // 默认值为空字符串
        this.font = jsonNode.path("font").asInt(0);                    // 默认值 0
    }

    // Getter methods
    public long getTime() {
        return time;
    }

    public long getSelfId() {
        return selfId;
    }

    public String getPostType() {
        return postType;
    }

    public String getMessageType() {
        return messageType;
    }

    public int getMessageId() {
        return messageId;
    }

    public long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public int getFont() {
        return font;
    }
}
