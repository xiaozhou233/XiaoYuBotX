package cn.xiaozhou233.xiaoyubot.onebot.api;

import java.util.HashMap;

public class send_group_msg extends wsAPI {
    public send_group_msg(long group_id, String message) {
        this.action = "send_group_msg";
        this.params.put("group_id", group_id);
        this.params.put("message", message);
    }
}
