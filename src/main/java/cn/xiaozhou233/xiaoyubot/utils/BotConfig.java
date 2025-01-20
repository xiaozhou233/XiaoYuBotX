package cn.xiaozhou233.xiaoyubot.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Objects;

public class BotConfig {
    private static final String configPath = "config.json";
    private final File config;
    private final JsonNode configNode;
    private static final ObjectMapper mapper = new ObjectMapper();

    public BotConfig() throws IOException {
            config = new File(configPath);
            if (!config.exists()) initConfig();
            configNode = mapper.readTree(config);
    }

    private void initConfig(){
        try (InputStream inputStream = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("config.json"), "Cannot read default config!");
             OutputStream outputStream = new FileOutputStream(configPath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode get(String key) {
        return configNode.get(key);
    }
}
