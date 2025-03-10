package cn.xiaozhou233.xiaoyubot.config;

import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Config {
    private static final Logger logger = LoggerFactory.getLogger("Config");
    private static final ObjectMapper mapper = new ObjectMapper();
    private final File configFile;
    private JsonNode config;

    public Config(String config) {
        configFile = new File(config);
        if (!configFile.exists()) {
            logger.error("Config file ({}) not found!", configFile.getAbsolutePath());
            return;
        }
        loadConfig();
        logger.debug("Config {} Loaded!", configFile.getName());
    }

    public JsonNode getConfig() {
        return config;
    }

    public boolean configExists() {
        return configFile.exists();
    }

    public void loadConfig() {
        try {
            config = mapper.readTree(configFile);
        } catch (IOException e) {
            logger.error("Failed to load config file ({})", configFile.getAbsolutePath(), e);
        }
    }
}
