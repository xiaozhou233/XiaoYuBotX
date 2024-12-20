package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class NoticeEvent {
    protected final long time;
    protected final long selfId;
    protected final String postType;
    protected final String noticeType;

    public long getTime() {
        return time;
    }

    public long getSelfId() {
        return selfId;
    }

    public String getPostType() {
        return postType;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public NoticeEvent(JsonNode jsonNode) {
        this.time = jsonNode.get("time").asLong();
        this.selfId = jsonNode.get("self_id").asLong();
        this.postType = jsonNode.get("post_type").asText();
        this.noticeType = jsonNode.get("notice_type").asText();
    }
}

