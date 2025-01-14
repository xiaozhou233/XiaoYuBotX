package cn.xiaozhou233.xiaoyubot.onebot.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    @JsonProperty("time")
    protected long time;

    @JsonProperty("self_id")
    protected long self_id;

    @JsonProperty("post_type")
    protected String post_type;
}
