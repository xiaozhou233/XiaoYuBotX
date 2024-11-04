package cn.xiaozhou233.xiaoyubot.onebotapi.api;

import cn.xiaozhou233.xiaoyubot.network.CallAPI;

import java.util.HashMap;
import java.util.Map;

public class delete_msg {
    private long message_id;
    public delete_msg(long message_id){
        this.message_id = message_id;
    }

    public void send(){
        Map<String, Object> map = new HashMap<>();
        map.put("message_id", message_id);
        CallAPI.call("delete_msg", map);
    }
}
