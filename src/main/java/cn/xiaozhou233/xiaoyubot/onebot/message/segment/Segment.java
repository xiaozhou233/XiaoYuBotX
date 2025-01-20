package cn.xiaozhou233.xiaoyubot.onebot.message.segment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Segment {
    @JsonProperty(value = "data")
    protected HashMap<String, Object> data = new HashMap<>();

    protected Object get(String name){
        return data.get(name);
    }
}
