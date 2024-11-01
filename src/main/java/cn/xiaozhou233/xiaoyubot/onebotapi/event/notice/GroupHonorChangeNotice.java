package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群成员荣誉变更事件
public class GroupHonorChangeNotice extends NoticeEvent {
    private String subType;
    private long groupId;
    private String honorType;
    private long userId;

    public String subType() {
        return subType;
    }

    public long groupId() {
        return groupId;
    }

    public String honorType() {
        return honorType;
    }

    public long userId() {
        return userId;
    }

    public GroupHonorChangeNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
        this.honorType = jsonNode.get("honor_type").asText();
        this.userId = jsonNode.get("user_id").asLong();
    }
}
