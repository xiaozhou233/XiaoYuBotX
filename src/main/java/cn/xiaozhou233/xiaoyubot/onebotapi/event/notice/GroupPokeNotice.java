package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群内戳一戳事件
public class GroupPokeNotice extends NoticeEvent {
    private final String subType;
    private final long groupId;
    private final long userId;
    private final long targetId;

    public String getSubType() {
        return subType;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getUserId() {
        return userId;
    }

    public long getTargetId() {
        return targetId;
    }

    public GroupPokeNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
        this.targetId = jsonNode.get("target_id").asLong();
    }
}
