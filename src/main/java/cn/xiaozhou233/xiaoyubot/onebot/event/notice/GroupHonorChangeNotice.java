package cn.xiaozhou233.xiaoyubot.onebot.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupHonorChangeNotice extends Notice {
    @JsonProperty("sub_type")
    protected String subType;
    @JsonProperty("group_id")
    protected long groupId;
    @JsonProperty("honor_type")
    protected String honorType;

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

    public String getHonorType() {
        return honorType;
    }

    public void setHonorType(String honorType) {
        this.honorType = honorType;
    }

    public static class HonorType {
        /*
        荣誉类型，分别表示龙王(talkative)、群聊之火(performer)、快乐源泉(emotion)
         */
        public static final String TALKAATIVE = "talkative";
        public static final String PERFORMER = "performer";
        public static final String EMOTION = "emotion";
    }
}
