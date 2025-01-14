package cn.xiaozhou233.xiaoyubot.onebot.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriendRecallNotice extends Notice {
    @JsonProperty("message_id")
    protected long messageId;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
