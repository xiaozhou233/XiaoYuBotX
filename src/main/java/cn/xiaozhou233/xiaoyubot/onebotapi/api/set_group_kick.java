package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;
import java.util.Map;

public class set_group_kick {
    private final long group_id;
    private final long user_id;
    private boolean reject_add_request = false;

    public set_group_kick(long group_id, long user_id) {
        this.group_id = group_id;
        this.user_id = user_id;
    }
    public set_group_kick(long group_id, long user_id, boolean reject_add_request) {
        this.group_id = group_id;
        this.user_id = user_id;
        this.reject_add_request = reject_add_request;
    }

    public void send(){
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", group_id);
        map.put("user_id", user_id);
        map.put("reject_add_request", reject_add_request);
        CallAPI.call("set_group_kick", map);
    }
}
