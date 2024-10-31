package cn.xiaozhou233.xiaoyubot.onebotapi.event.message;

import com.fasterxml.jackson.databind.JsonNode;

// 群消息事件
public class GroupMessageEvent extends MessageEvent {
    private String subType;
    private long groupId;
    private Anonymous anonymous;
    private Sender sender;

    public GroupMessageEvent(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
        JsonNode anonymousNode = jsonNode.get("anonymous");
        if (anonymousNode != null && !anonymousNode.isNull()) {
            this.anonymous = new Anonymous(anonymousNode);
        }
        JsonNode senderNode = jsonNode.get("sender");
        if (senderNode != null) {
            this.sender = new Sender(senderNode);
        }
    }

    public static class Anonymous {
        private long id;
        private String name;
        private String flag;

        public Anonymous(JsonNode anonymousNode) {
            this.id = anonymousNode.get("id").asLong();
            this.name = anonymousNode.get("name").asText();
            this.flag = anonymousNode.get("flag").asText();
        }
    }

    public static class Sender {
        private long userId;
        private String nickname;
        private String card;
        private String sex;
        private int age;
        private String area;
        private String level;
        private String role;
        private String title;

        public Sender(JsonNode senderNode) {
            this.userId = senderNode.get("user_id").asLong();
            this.nickname = senderNode.get("nickname").asText();
            this.card = senderNode.get("card").asText();
            this.sex = senderNode.get("sex").asText();
            this.age = senderNode.get("age").asInt();
            this.area = senderNode.get("area").asText();
            this.level = senderNode.get("level").asText();
            this.role = senderNode.get("role").asText();
            this.title = senderNode.get("title").asText();
        }
    }
}
