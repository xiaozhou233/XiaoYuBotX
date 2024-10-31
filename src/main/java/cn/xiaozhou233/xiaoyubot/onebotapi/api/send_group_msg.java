package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;

public class send_group_msg {
    public long group_id;
    public String message;
    public boolean auto_escape;
    public send_group_msg(long group_id, String message) {
        this.group_id = group_id;
        this.message = message;
        this.auto_escape = true;
    }
    public void send(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("group_id", group_id);
        map.put("message", message);
        map.put("auto_escape", auto_escape);
        CallAPI.call("send_group_msg", map);
    }

    public void setAuto_escape(boolean auto_escape) {
        this.auto_escape = auto_escape;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public static void main(String[] args) {
        new send_group_msg(650559268, "test").send();
    }
}
