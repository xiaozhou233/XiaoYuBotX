package cn.xiaozhou233.xiaoyubot.onebot.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupRecallNotice extends Notice {
    @JsonProperty("group_id")
    protected long groupId;
    @JsonProperty("operator_id")
    protected long operatorId;
    @JsonProperty("message_id")
    protected long messageId;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
