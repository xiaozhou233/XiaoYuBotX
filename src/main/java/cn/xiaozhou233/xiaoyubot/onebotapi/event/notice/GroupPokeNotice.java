package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群内戳一戳事件
public class GroupPokeNotice extends NoticeEvent {
    private String subType;
    private long groupId;
    private long userId;
    private long targetId;

    public GroupPokeNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
        this.targetId = jsonNode.get("target_id").asLong();
    }
}
