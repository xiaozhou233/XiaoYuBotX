package cn.xiaozhou233.xiaoyubot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Info {
    private static final Logger logger = LoggerFactory.getLogger("XiaoYuBotX");

    public static final String NAME = "XiaoYuBotX";
    public static final String VERSION = "2.0.1";
    public static final String VERSION_CODE = "0002";
    public static final String AUTHOR = "xiaozhou233";

    public static void printInfo() {
        logger.info("======== INFO ========");
        logger.info("{} v{}.{}", NAME, VERSION, VERSION_CODE);
        logger.info("By {}.", AUTHOR);
        logger.info("=====================");
    }
}
