package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群成员增加事件
public class GroupIncreaseNotice extends NoticeEvent {
    private String subType;
    private long groupId;
    private long operatorId;
    private long userId;

    public GroupIncreaseNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
        this.operatorId = jsonNode.get("operator_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
    }
}
