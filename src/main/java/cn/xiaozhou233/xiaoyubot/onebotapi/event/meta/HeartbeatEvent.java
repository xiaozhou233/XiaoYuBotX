package cn.xiaozhou233.xiaoyubot.onebotapi.event.meta;

import com.fasterxml.jackson.databind.JsonNode;

// 心跳事件类
public class HeartbeatEvent extends MetaEvent {
    private final JsonNode status;
    private final long interval;

    public HeartbeatEvent(JsonNode jsonNode) {
        super(jsonNode);
        this.status = jsonNode.get("status");
        this.interval = jsonNode.get("interval").asLong();
    }

    public JsonNode getStatus() {
        return status;
    }

    public long getInterval() {
        return interval;
    }
}
