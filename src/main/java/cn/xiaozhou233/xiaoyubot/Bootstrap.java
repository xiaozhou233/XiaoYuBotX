package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.utils.Utils;
import cn.xiaozhou233.xiaoyubot.Info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Bootstrap {
    private static final Logger logger = LoggerFactory.getLogger("Bootstrap");
    private static XiaoYuBotX instance;
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> logger.error("Exception in Thread {}: ", t.getName(), e));
        Info.printInfo();

        Utils.check(logger);

        instance = new XiaoYuBotX();
    }

    public static XiaoYuBotX getInstance() {
        return instance;
    }
}
