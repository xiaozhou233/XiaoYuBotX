package cn.xiaozhou233.xiaoyubot.onebot.message.segment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Text extends Segment{
    @JsonProperty(value = "text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
