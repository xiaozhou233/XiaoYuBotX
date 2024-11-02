package cn.xiaozhou233.xiaoyubot.onebotapi.event.meta;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class MetaEvent {
    protected long time;
    protected long selfId;
    protected String postType;
    protected String metaEventType;

    public long getTime() {
        return time;
    }

    public long getSelfId() {
        return selfId;
    }

    public String getPostType() {
        return postType;
    }

    public String getMetaEventType() {
        return metaEventType;
    }

    public MetaEvent(JsonNode jsonNode) {
        this.time = jsonNode.get("time").asLong();
        this.selfId = jsonNode.get("self_id").asLong();
        this.postType = jsonNode.get("post_type").asText();
        this.metaEventType = jsonNode.get("meta_event_type").asText();
    }
}

