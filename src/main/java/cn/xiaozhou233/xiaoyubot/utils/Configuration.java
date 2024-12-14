package cn.xiaozhou233.xiaoyubot.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Configuration {
    private static final String configPath = "config.json";
    private static final File configFile = new File(configPath);
    private static JsonNode configNode;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TaggedLogger logger = Logger.tag("BotConfig");

    public Configuration() {
        if (!configFile.exists()) {
            try {
                boolean created = configFile.createNewFile();
                if (!created) {
                    logger.error("Failed to create config file.");
                }
                initConfig();
            } catch (IOException e) {
                logger.error("Failed to create config file.");
                logger.trace(e);
            }
        }

        // read content
        try {
            configNode = objectMapper.readTree(configFile);
            logger.info("Config file loaded successfully.");
        }catch (Exception e) {
            logger.error("Failed to read config file.");
            logger.trace(e);
        }
    }

    private void initConfig() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.json")) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 FileOutputStream fos = new FileOutputStream(configFile)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    fos.write(line.getBytes());
                    fos.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            logger.error("Failed to initialize config file.");
        }
    }
    public JsonNode getConfigNode() {
        return configNode;
    }
}
