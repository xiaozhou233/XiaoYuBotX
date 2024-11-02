package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群禁言事件
public class GroupBanNotice extends NoticeEvent {
    private String subType;
    private long groupId;
    private long operatorId;
    private long userId;
    private long duration;

    public String getSubType() {
        return subType;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public long getUserId() {
        return userId;
    }

    public long getDuration() {
        return duration;
    }

    public GroupBanNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
        this.operatorId = jsonNode.get("operator_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
        this.duration = jsonNode.get("duration").asLong();
    }
}
