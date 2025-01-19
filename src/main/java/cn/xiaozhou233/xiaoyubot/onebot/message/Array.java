package cn.xiaozhou233.xiaoyubot.onebot.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Array<T> {
    @JsonProperty("type")
    protected String type;

    @JsonProperty("data")
    protected T data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
