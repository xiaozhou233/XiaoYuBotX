package cn.xiaozhou233.xiaoyubot.onebot.event.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupAddRequest extends Request {
    @JsonProperty("group_id")
    protected long group_id;
    @JsonProperty("sub_type")
    protected String sub_type;

    public long getGroupId() {
        return group_id;
    }

    public void setGroupId(long group_id) {
        this.group_id = group_id;
    }

    public String getSubType() {
        return sub_type;
    }

    public void setSubType(String sub_type) {
        this.sub_type = sub_type;
    }

    public static class SubType {
        public static final String ADD = "add";
        public static final String INVITE = "invite";
    }
}
