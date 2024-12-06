package cn.xiaozhou233.xiaoyubot.onebotapi.event.message;

import cn.xiaozhou233.xiaoyubot.onebotapi.api.send_group_msg;
import com.fasterxml.jackson.databind.JsonNode;

// 私聊消息事件
public class PrivateMessageEvent extends MessageEvent {
    private final String subType;
    private Sender sender;

    public String getSubType() {
        return subType;
    }

    public Sender getSender() {
        return sender;
    }

    public PrivateMessageEvent(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.path("sub_type").asText("Unknown"); // 默认值为 "Unknown"
        JsonNode senderNode = jsonNode.path("sender");
        if (!senderNode.isMissingNode()) { // 确保 senderNode 存在
            this.sender = new Sender(senderNode);
        }
    }

    public static class Sender {
        private final long userId;
        private final String nickname;
        private final String sex;
        private final int age;

        public long getUserId() {
            return userId;
        }

        public String getNickname() {
            return nickname;
        }

        public String getSex() {
            return sex;
        }

        public int getAge() {
            return age;
        }

        public Sender(JsonNode senderNode) {
            this.userId = senderNode.path("user_id").asLong(0);          // 默认值为 0
            this.nickname = senderNode.path("nickname").asText("Unknown"); // 默认值为 "Unknown"
            this.sex = senderNode.path("sex").asText("Unknown");         // 默认值为 "Unknown"
            this.age = senderNode.path("age").asInt(0);                  // 默认值为 0
        }
    }

    public void fastReply(String message) {
        String content = "[CQ:reply,id=%d] %s".formatted(messageId, message);
        new send_group_msg(sender.userId, content).send();
    }
}
