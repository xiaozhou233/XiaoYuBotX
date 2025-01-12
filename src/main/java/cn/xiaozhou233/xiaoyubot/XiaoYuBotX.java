package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.onebot.APIClient;
import cn.xiaozhou233.xiaoyubot.onebot.EventClient;
import cn.xiaozhou233.xiaoyubot.onebot.api.send_group_msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XiaoYuBotX {
    private static final Logger logger = LoggerFactory.getLogger("XiaoYuBotX");
    public static EventClient eventClient;
    public static void main(String[] args) {
        APIClient.run("ws://192.168.2.222:3001");
        new send_group_msg(650559268L, "hello world").send();
    }
}