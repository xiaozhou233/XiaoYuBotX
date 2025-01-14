package cn.xiaozhou233.xiaoyubot.onebot.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupAdminNotice extends Notice{
    @JsonProperty("sub_type")
    private String subType;
    @JsonProperty("group_id")
    private long groupId;

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

    public static class SubType {
         public static final String SET = "set";
         public static final String UNSET = "unset";
    }
}
