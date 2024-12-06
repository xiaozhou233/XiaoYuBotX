package cn.xiaozhou233.xiaoyubot.onebotapi.event.request;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class RequestEvent {
    protected final long time;
    protected final long selfId;
    protected final String postType;
    protected final String requestType;
    protected final long userId;
    protected final String comment;
    protected final String flag;

    public long getTime() {
        return time;
    }

    public long getSelfId() {
        return selfId;
    }

    public String getPostType() {
        return postType;
    }

    public String getRequestType() {
        return requestType;
    }

    public long getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public String getFlag() {
        return flag;
    }

    public RequestEvent(JsonNode jsonNode) {
        this.time = jsonNode.get("time").asLong();
        this.selfId = jsonNode.get("self_id").asLong();
        this.postType = jsonNode.get("post_type").asText();
        this.requestType = jsonNode.get("request_type").asText();
        this.userId = jsonNode.get("user_id").asLong();
        this.comment = jsonNode.get("comment").asText();
        this.flag = jsonNode.get("flag").asText();
    }
}

