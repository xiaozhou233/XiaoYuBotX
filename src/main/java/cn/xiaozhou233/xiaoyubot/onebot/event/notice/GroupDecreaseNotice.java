package cn.xiaozhou233.xiaoyubot.onebot.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupDecreaseNotice extends Notice {
    @JsonProperty("sub_type")
    private String subType;
    @JsonProperty("group_id")
    private long groupId;
    @JsonProperty("operator_id")
    private long operatorId;

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public static class SubType {
        public static final String LEAVE = "leave";
        public static final String KICK = "kick";
        public static final String KICK_ME = "kick_me";
    }
}
