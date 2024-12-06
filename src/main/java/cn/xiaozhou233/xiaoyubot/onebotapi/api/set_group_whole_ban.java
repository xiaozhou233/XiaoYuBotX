package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;
import java.util.Map;

public class set_group_whole_ban {
    private final long group_id;
    private final boolean enable;

    public set_group_whole_ban(long group_id, boolean enable) {
        this.group_id = group_id;
        this.enable = enable;
    }

    public void send(){
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", group_id);
        map.put("enable", enable);
        CallAPI.call("set_group_whole_ban", map);
    }
}
