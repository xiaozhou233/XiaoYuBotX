package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群消息撤回事件
public class GroupRecallNotice extends NoticeEvent {
    private final long groupId;
    private final long userId;
    private final long operatorId;
    private final long messageId;

    public long groupId() {
        return groupId;
    }

    public long userId() {
        return userId;
    }

    public long operatorId() {
        return operatorId;
    }

    public long messageId() {
        return messageId;
    }

    public GroupRecallNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.groupId = jsonNode.get("group_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
        this.operatorId = jsonNode.get("operator_id").asLong();
        this.messageId = jsonNode.get("message_id").asLong();
    }
}
