package cn.xiaozhou233.xiaoyubot.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public Configuration() {
        if (!configFile.exists()) {
            try {
                boolean created = configFile.createNewFile();
                if (!created) {
                    System.out.println("[Error] Failed to create config file.");
                }
                initConfig();
            } catch (IOException e) {
                System.out.println("[Error] Failed to create config file.");
                throw new RuntimeException(e);
            }
        }

        // read content
        try {
            configNode = objectMapper.readTree(configFile);
            System.out.println("[Info] Config file loaded successfully.");
        }catch (Exception e) {
            System.out.println("[Error] Failed to read config file.");
            throw new RuntimeException(e);
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
            System.out.println("[Error] Failed to initialize config file.");
            throw new RuntimeException(e);
        }
    }
    public JsonNode getConfigNode() {
        return configNode;
    }
}
