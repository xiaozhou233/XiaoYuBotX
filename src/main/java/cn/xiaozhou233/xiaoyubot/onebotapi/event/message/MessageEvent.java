package cn.xiaozhou233.xiaoyubot.onebotapi.event.message;

import cn.xiaozhou233.xiaoyubot.onebotapi.message.MessageSegment;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public abstract class MessageEvent {
    protected long time;
    protected long selfId;
    protected String postType;
    protected String messageType;
    protected int messageId;
    protected long userId;
    protected List<MessageSegment> messageSegments;
    protected String rawMessage;
    protected int font;

    public MessageEvent(JsonNode jsonNode) {
        this.time = jsonNode.path("time").asLong(0);
        this.selfId = jsonNode.path("self_id").asLong(0);
        this.postType = jsonNode.path("post_type").asText("Unknown");
        this.messageType = jsonNode.path("message_type").asText("Unknown");
        this.messageId = jsonNode.path("message_id").asInt(0);
        this.userId = jsonNode.path("user_id").asLong(0);
        this.rawMessage = jsonNode.path("raw_message").asText("");
        this.font = jsonNode.path("font").asInt(0);

        // 处理多个 MessageSegment
        JsonNode messageNode = jsonNode.path("message");
        this.messageSegments = MessageSegment.fromJsonArray(messageNode);
    }

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

    public List<MessageSegment> getMessageSegments() {
        return messageSegments;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public int getFont() {
        return font;
    }
}
