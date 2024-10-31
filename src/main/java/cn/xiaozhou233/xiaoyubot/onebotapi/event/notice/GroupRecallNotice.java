package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群消息撤回事件
public class GroupRecallNotice extends NoticeEvent {
    private long groupId;
    private long userId;
    private long operatorId;
    private long messageId;

    public GroupRecallNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.groupId = jsonNode.get("group_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
        this.operatorId = jsonNode.get("operator_id").asLong();
        this.messageId = jsonNode.get("message_id").asLong();
    }
}
