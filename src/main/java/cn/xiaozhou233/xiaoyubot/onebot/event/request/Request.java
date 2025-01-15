package cn.xiaozhou233.xiaoyubot.onebot.event.request;

import cn.xiaozhou233.xiaoyubot.onebot.event.Event;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request extends Event {
    @JsonProperty("request_type")
    protected String request_type;
    @JsonProperty("user_id")
    protected long user_id;
    @JsonProperty("comment")
    protected String comment;
    @JsonProperty("flag")
    protected String flag;

    public String getRequestType() {
        return request_type;
    }

    public void setRequestType(String request_type) {
        this.request_type = request_type;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
