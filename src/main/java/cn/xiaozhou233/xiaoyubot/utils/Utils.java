package cn.xiaozhou233.xiaoyubot.utils;

import org.slf4j.Logger;

import java.io.*;

public class Utils {
    public static void check(Logger logger) {
        try {
            // Config
            File configFile = new File("config.json");
            if (!configFile.exists()){
                logger.info("Init Config File...");
                configFile.createNewFile();
                // Copy resources/config.json
                try (InputStream in = ClassLoader.getSystemResourceAsStream("config.json"); OutputStream out = new FileOutputStream(configFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                logger.info("Config Initialized");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
