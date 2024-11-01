package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 好友添加事件
public class FriendAddNotice extends NoticeEvent {
    private long userId;

    public FriendAddNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.userId = jsonNode.get("user_id").asLong();
    }

    public long getUserId() {
        return userId;
    }
}
