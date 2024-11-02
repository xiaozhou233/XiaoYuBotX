package cn.xiaozhou233.xiaoyubot.onebotapi.message;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.List;

public class MessageSegment {
    private String type;
    private ObjectNode data;

    public MessageSegment(String type, ObjectNode data) {
        this.type = type;
        this.data = data;
    }

    public MessageSegment(JsonNode jsonNode) {
        if (jsonNode.has("type")) {
            this.type = jsonNode.get("type").asText();
        }
        if (jsonNode.has("data") && jsonNode.get("data").isObject()) {
            this.data = (ObjectNode) jsonNode.get("data");
        }
    }

    public String getType() {
        return type;
    }

    public ObjectNode getData() {
        return data;
    }

    public static List<MessageSegment> fromJsonArray(JsonNode arrayNode) {
        List<MessageSegment> segments = new ArrayList<>();
        if (arrayNode.isArray()) {
            for (JsonNode node : arrayNode) {
                segments.add(new MessageSegment(node));
            }
        } else {
            segments.add(new MessageSegment(arrayNode));
        }
        return segments;
    }
}
