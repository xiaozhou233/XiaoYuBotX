package cn.xiaozhou233.xiaoyubot.onebot.api;

public class send_group_msg extends API{
    public send_group_msg(long group_id, String message) {
        this.action = "send_group_msg";
        this.params.put("group_id", group_id);
        this.params.put("message", message);
    }

    public send_group_msg(long group_id, String message, int auto_escape) {
        this.action = "send_group_msg";
        this.params.put("group_id", group_id);
        this.params.put("message", message);
        this.params.put("auto_escape", auto_escape);
    }
}
