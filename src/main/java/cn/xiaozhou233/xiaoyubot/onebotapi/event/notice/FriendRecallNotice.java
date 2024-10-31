package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 好友消息撤回事件
public class FriendRecallNotice extends NoticeEvent {
    private long userId;
    private long messageId;

    public FriendRecallNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.userId = jsonNode.get("user_id").asLong();
        this.messageId = jsonNode.get("message_id").asLong();
    }
}
