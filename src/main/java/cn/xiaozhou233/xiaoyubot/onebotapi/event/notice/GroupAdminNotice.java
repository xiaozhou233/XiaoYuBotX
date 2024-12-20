package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群管理员变动事件
public class GroupAdminNotice extends NoticeEvent {
    private final String subType;
    private final long groupId;
    private final long userId;

    public String subType() {
        return subType;
    }

    public long groupId() {
        return groupId;
    }

    public long userId() {
        return userId;
    }

    public GroupAdminNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
    }
}
