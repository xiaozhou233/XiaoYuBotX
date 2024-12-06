package cn.xiaozhou233.xiaoyubot.onebotapi.event.message;

import cn.xiaozhou233.xiaoyubot.onebotapi.api.send_group_msg;
import com.fasterxml.jackson.databind.JsonNode;

// 群消息事件
public class GroupMessageEvent extends MessageEvent {
    private final String subType;
    private final long groupId;
    private Anonymous anonymous;
    private Sender sender;

    public GroupMessageEvent(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.path("sub_type").asText("Unknown");  // 默认值为 "Unknown"
        this.groupId = jsonNode.path("group_id").asLong(0);          // 默认值为 0

        JsonNode anonymousNode = jsonNode.path("anonymous");
        if (!anonymousNode.isMissingNode() && !anonymousNode.isNull()) {
            this.anonymous = new Anonymous(anonymousNode);
        }

        JsonNode senderNode = jsonNode.path("sender");
        if (!senderNode.isMissingNode()) {
            this.sender = new Sender(senderNode);
        }
    }

    public static class Anonymous {
        private final long id;
        private final String name;
        private final String flag;

        public Anonymous(JsonNode anonymousNode) {
            this.id = anonymousNode.path("id").asLong(0);                // 默认值为 0
            this.name = anonymousNode.path("name").asText("Unknown");    // 默认值为 "Unknown"
            this.flag = anonymousNode.path("flag").asText("Unknown");    // 默认值为 "Unknown"
        }

        // Getter methods
        public long getId() { return id; }
        public String getName() { return name; }
        public String getFlag() { return flag; }
    }

    public static class Sender {
        private final long userId;
        private final String nickname;
        private final String card;
        private final String sex;
        private final int age;
        private final String area;
        private final String level;
        private final String role;
        private final String title;

        public Sender(JsonNode senderNode) {
            this.userId = senderNode.path("user_id").asLong(0);                // 默认值为 0
            this.nickname = senderNode.path("nickname").asText("Unknown");     // 默认值为 "Unknown"
            this.card = senderNode.path("card").asText("Unknown");             // 默认值为 "Unknown"
            this.sex = senderNode.path("sex").asText("Unknown");               // 默认值为 "Unknown"
            this.age = senderNode.path("age").asInt(0);                        // 默认值为 0
            this.area = senderNode.path("area").asText("Unknown");             // 默认值为 "Unknown"
            this.level = senderNode.path("level").asText("Unknown");           // 默认值为 "Unknown"
            this.role = senderNode.path("role").asText("Unknown");             // 默认值为 "Unknown"
            this.title = senderNode.path("title").asText("Unknown");           // 默认值为 "Unknown"
        }

        // Getter methods
        public long getUserId() { return userId; }
        public String getNickname() { return nickname; }
        public String getCard() { return card; }
        public String getSex() { return sex; }
        public int getAge() { return age; }
        public String getArea() { return area; }
        public String getLevel() { return level; }
        public String getRole() { return role; }
        public String getTitle() { return title; }
    }

    // Getter methods for GroupMessageEvent
    public String getSubType() { return subType; }
    public long getGroupId() { return groupId; }
    public Anonymous getAnonymous() { return anonymous; }
    public Sender getSender() { return sender; }


    public void fastReply(String message) {
        String content = "[CQ:reply,id=%d] %s".formatted(messageId, message);
        new send_group_msg(groupId, content).send();
    }

    public void fastReply(String message, boolean isReplyFormat) {
        String content;
        if(isReplyFormat){
            content = "[CQ:reply,id=%s] %s".formatted(String.valueOf(messageId), message);
        }else {
            content = message;
        }

        new send_group_msg(groupId, content).send();
    }
}
