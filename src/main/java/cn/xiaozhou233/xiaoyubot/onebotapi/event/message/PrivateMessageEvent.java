package cn.xiaozhou233.xiaoyubot.onebotapi.event.message;

import com.fasterxml.jackson.databind.JsonNode;

// 私聊消息事件
public class PrivateMessageEvent extends MessageEvent {
    private String subType;
    private Sender sender;

    public PrivateMessageEvent(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        JsonNode senderNode = jsonNode.get("sender");
        if (senderNode != null) {
            this.sender = new Sender(senderNode);
        }
    }

    public static class Sender {
        private long userId;
        private String nickname;
        private String sex;
        private int age;

        public Sender(JsonNode senderNode) {
            this.userId = senderNode.get("user_id").asLong();
            this.nickname = senderNode.get("nickname").asText();
            this.sex = senderNode.get("sex").asText();
            this.age = senderNode.get("age").asInt();
        }
    }
}
