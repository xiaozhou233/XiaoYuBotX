package cn.xiaozhou233.xiaoyubot.onebotapi.event.meta;

import com.fasterxml.jackson.databind.JsonNode;

// 生命周期事件类
public class LifecycleEvent extends MetaEvent {
    private final String subType;

    public LifecycleEvent(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
    }

    public String getSubType() {
        return subType;
    }
}
