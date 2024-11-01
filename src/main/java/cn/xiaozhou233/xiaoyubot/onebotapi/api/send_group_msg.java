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

    // 使用流式接口（链式调用）
    public send_group_msg setAutoEscape(boolean autoEscape) {
        this.autoEscape = autoEscape;
        return this;
    }

    public send_group_msg setMessage(String message) {
        this.message = message;
        return this;
    }

    public send_group_msg setGroupId(long groupId) {
        this.groupId = groupId;
        return this;
    }

    public static void main(String[] args) {
        new send_group_msg(650559268, "test").send();
    }
}
