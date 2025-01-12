package cn.xiaozhou233.xiaoyubot.onebot.api;

import cn.xiaozhou233.xiaoyubot.onebot.APIClient;

import java.util.HashMap;

public class wsAPI {
    protected String action;
    protected HashMap<String, Object> params = new HashMap<>();
    public void send(){
        try {
            APIClient.callAPI(action, params);
        } catch (Exception e) {
            throw new RuntimeException("API call failed: " + e.getMessage());
        }
    }
}
