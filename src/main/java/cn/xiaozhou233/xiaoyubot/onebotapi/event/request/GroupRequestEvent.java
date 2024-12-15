package cn.xiaozhou233.xiaoyubot.onebotapi.event.request;

import cn.xiaozhou233.xiaoyubot.onebotapi.api.set_group_add_request;
import com.fasterxml.jackson.databind.JsonNode;

// 加群请求/邀请事件类
public class GroupRequestEvent extends RequestEvent {
    private final String subType;
    private final long groupId;

    public GroupRequestEvent(JsonNode jsonNode) {
        super(jsonNode);
        this.subType = jsonNode.get("sub_type").asText();
        this.groupId = jsonNode.get("group_id").asLong();
    }

    public String getSubType() {
        return subType;
    }

    public long getGroupId() {
        return groupId;
    }

    public fastAction fastAction(){
        return new fastAction();
    }

    public class fastAction{
        public void accept() {
            String flag = getFlag();
            String sub_type = getSubType();
            boolean approve = true;
            new set_group_add_request(flag, sub_type, approve, "").send();
        }

        public void reject() {
            String flag = getFlag();
            String sub_type = getSubType();
            boolean approve = false;
            new set_group_add_request(flag, sub_type, approve, "").send();
        }

        public void reject(String reason) {
            String flag = getFlag();
            String sub_type = getSubType();
            boolean approve = false;
            new set_group_add_request(flag, sub_type, approve, reason).send();
        }
    }
}
