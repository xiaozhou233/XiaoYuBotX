package cn.xiaozhou233.xiaoyubot.onebot.api;


import cn.xiaozhou233.xiaoyubot.core.OneBotClient;

import java.util.HashMap;

public class API {
    protected String action;
    protected HashMap<String, Object> params = new HashMap<>();

    public void send() {
        OneBotClient.callAPI(action, params);
    }
}
