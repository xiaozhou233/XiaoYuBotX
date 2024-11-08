package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;
import java.util.Map;

public class send_private_msg {
    private long user_id;
    private String message;
    private boolean auto_escape = true;

    public send_private_msg(long user_id, String message) {
        this.user_id = user_id;
        this.message = message;
    }
    public send_private_msg(long user_id, String message, boolean auto_escape) {
        this.user_id = user_id;
        this.message = message;
        this.auto_escape = auto_escape;
    }

    public void send() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("message", message);
        params.put("auto_escape", auto_escape);
        CallAPI.call("send_group_msg", params);
    }
}
