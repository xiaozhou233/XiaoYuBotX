package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;
import java.util.Map;

public class set_friend_add_request {
    private String flag;
    private boolean approve;
    private String remark;

    public set_friend_add_request(String flag, boolean approve, String remark) {
        this.flag = flag;
        this.approve = approve;
        this.remark = remark;
    }
    public void send() {
        Map<String, Object> params = new HashMap<>();
        params.put("flag", flag);
        params.put("approve", approve);
        params.put("remark", remark);
        CallAPI.call("set_friend_add_request", params);
    }
}
