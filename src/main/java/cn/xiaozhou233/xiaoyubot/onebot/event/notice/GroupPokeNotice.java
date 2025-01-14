package cn.xiaozhou233.xiaoyubot.onebot.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupPokeNotice extends Notice {
    @JsonProperty("sub_type")
    protected String subType;
    @JsonProperty("group_id")
    protected long groupId;
    @JsonProperty("target_id")
    protected long targetId;

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

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }
}
