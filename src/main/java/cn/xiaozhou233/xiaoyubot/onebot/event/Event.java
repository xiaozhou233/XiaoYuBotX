package cn.xiaozhou233.xiaoyubot.onebot.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    @JsonProperty("time")
    protected long time;

    @JsonProperty("self_id")
    protected long self_id;

    @JsonProperty("post_type")
    protected String post_type;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSelfID() {
        return self_id;
    }

    public void setSelfID(long self_id) {
        this.self_id = self_id;
    }

    public String getPostType() {
        return post_type;
    }

    public void setPostType(String post_type) {
        this.post_type = post_type;
    }
}
