package cn.xiaozhou233.xiaoyubot.onebot.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Array<T> {
    @JsonProperty("type")
    protected String type;

    @JsonProperty("data")
    protected T data;
}
