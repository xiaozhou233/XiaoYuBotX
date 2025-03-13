package cn.xiaozhou233.xiaoyubot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Utils {
    private static final Logger logger = LoggerFactory.getLogger("Utils");
    private static final ObjectMapper mapper = new ObjectMapper();

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

    public static void sleep(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
    }

    public static JsonNode handleJson(String t) {
        try {
            return mapper.readTree(t);
        } catch (JsonProcessingException e) {
            logger.error("Error while parsing json: {}", t, e);
            throw new RuntimeException(e);
        }
    }
}
