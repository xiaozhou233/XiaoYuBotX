package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;
import java.util.Map;

public class send_group_msg {
    private long groupId;
    private String message;
    private boolean autoEscape = true;

    // 使用构造器重载，让用户选择是否提供autoEscape
    public send_group_msg(long groupId, String message) {
        this.groupId = groupId;
        this.message = message;
    }

    public send_group_msg(long groupId, String message, boolean autoEscape) {
        this.groupId = groupId;
        this.message = message;
        this.autoEscape = autoEscape;
    }

    public void send() {
        Map<String, Object> params = new HashMap<>();
        params.put("group_id", groupId);
        params.put("message", message);
        params.put("auto_escape", autoEscape);
        CallAPI.call("send_group_msg", params);
    }

}
