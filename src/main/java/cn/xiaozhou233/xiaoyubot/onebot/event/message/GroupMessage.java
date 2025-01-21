package cn.xiaozhou233.xiaoyubot.onebot.event.message;

import cn.xiaozhou233.xiaoyubot.onebot.api.send_group_msg;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupMessage extends Message<GroupMessage.Sender> {
    @JsonProperty("group_id")
    private long groupId;

    @JsonProperty("anonymous")
    private Anonymous anonymous;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public Anonymous getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Anonymous anonymous) {
        this.anonymous = anonymous;
    }

    public fastAction fastAction() {
        return new fastAction();
    }

    public static class Anonymous {
        private long id;
        private String name;
        private String flag;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }

    public static class Sender extends Message.Sender {
        private String card;
        private String area;
        private String level;
        private String role;
        private String title;

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public final class fastAction {
        public void send(String message) {
            new send_group_msg(groupId, message).send();
        }

        public void send(String message, boolean at) {
            new send_group_msg(groupId, "[CQ:at,qq=%d] %s".formatted(
                    user_id, message
            )).send();
        }
    }
}
