package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群红包运气王事件
public class GroupLuckyKingNotice extends NoticeEvent {
    private final String subType;
    private final long groupId;
    private final long userId;
    private final long targetId;

    public String subType() {
        return subType;
    }

    public long groupId() {
        return groupId;
    }

    public long userId() {
        return userId;
    }

    public long targetId() {
        return targetId;
    }

    public GroupLuckyKingNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
        this.targetId = jsonNode.get("target_id").asLong();
    }
}
