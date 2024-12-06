package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;
import java.util.Map;

public class set_group_ban {
    private final long group_id;
    private final long user_id;
    private final long duration;
    public set_group_ban(long group_id, long user_id, long duration) {
        this.group_id = group_id;
        this.user_id = user_id;
        this.duration = duration;
    }
    public void send(){
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", group_id);
        map.put("user_id", user_id);
        map.put("duration", duration);
        CallAPI.call("set_group_ban", map);
    }
}
