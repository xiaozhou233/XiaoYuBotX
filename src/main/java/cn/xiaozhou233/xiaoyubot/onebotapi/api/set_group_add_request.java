package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;
import java.util.Map;

public class set_group_add_request {
    private final String flag;
    private final String sub_type;
    private final boolean approve;
    private final String reason;

    public set_group_add_request(String flag, String sub_type, boolean approve, String reason) {
        this.flag = flag;
        this.sub_type = sub_type;
        this.approve = approve;
        this.reason = reason;
    }
    public void send(){
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        map.put("sub_type", sub_type);
        map.put("approve", approve);
        map.put("reason", reason);
        CallAPI.call("set_group_add_request", map);
    }
}