package cn.xiaozhou233.xiaoyubot.onebot.event.notice;

import cn.xiaozhou233.xiaoyubot.onebot.event.Event;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Notice extends Event {
    @JsonProperty("notice_type")
    protected String noticeType;
    @JsonProperty("user_id")
    protected long userId;

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
