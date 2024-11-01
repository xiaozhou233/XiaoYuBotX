package cn.xiaozhou233.xiaoyubot.onebotapi.event.request;

import com.fasterxml.jackson.databind.JsonNode;

// 加群请求/邀请事件类
public class GroupRequestEvent extends RequestEvent {
    private String subType;
    private long groupId;

    public String subType() {
        return subType;
    }

    public long groupId() {
        return groupId;
    }

    public GroupRequestEvent(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
    }

    public String getSubType() {
        return subType;
    }

    public long getGroupId() {
        return groupId;
    }
}
