package cn.xiaozhou233.xiaoyubot;

import cn.xiaozhou233.xiaoyubot.Utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Bootstrap {
    private static final Logger logger = LoggerFactory.getLogger("Bootstrap");
    public static void main(String[] args) {
        version();
        check();
    }

    private static void version() {
        System.out.println(VersionInfo.Logo);
        logger.info(VersionInfo.line);
        logger.info(VersionInfo.NAME);
        logger.info("Version: {}", VersionInfo.VERSION);
        logger.info(VersionInfo.line);
        logger.info("Starting...");
    }

    private static void check() {
        // XiaoYuBotX Config
        File config = new File("config.json");
        if (!config.exists()) {
            logger.info("It's seems first time running, init config.json...");
            FileUtils.copyFile(
                    ClassLoader.getSystemResourceAsStream("config.json"),
                    "config.json"
            );
        }
    }
}
