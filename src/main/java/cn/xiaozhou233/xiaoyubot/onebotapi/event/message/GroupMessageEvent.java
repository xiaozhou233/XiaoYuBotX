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
        private long id;
        private String name;
        private String flag;

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
}
