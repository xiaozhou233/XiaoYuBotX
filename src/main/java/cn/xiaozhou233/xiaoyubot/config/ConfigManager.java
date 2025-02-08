package cn.xiaozhou233.xiaoyubot.config;

import cn.xiaozhou233.xiaoyubot.plugins.Plugin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class ConfigManager {
    private final String path;
    private final Plugin plugin;
    private final String name;
    private final File file;
    private JsonNode config;

    private static final ObjectMapper mapper = new ObjectMapper();


    public ConfigManager(Plugin plugin, String name) {
        path = "config/%s/config.json".formatted(name);
        this.plugin = plugin;
        this.name = name;
        file = new File(path);
        if (!file.exists()) initConfigPath();
        try(InputStream inputStream = Files.newInputStream(file.toPath())) {
            config = mapper.readTree(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode get(String key) {
        return config.get(key);
    }

    public void set(String key, Object value) {
        try {
            Map<String, Object> map = mapper.convertValue(config, new TypeReference<>() {});
            map.put(key, value);
            config = mapper.valueToTree(map);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set config value", e);
        }
    }


    public void saveConfig() throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, config);
        }
    }

    private void initConfigPath() {
        // Config Dictionary
        File configDir = new File("config");
        if (!configDir.exists()) configDir.mkdir();
        // Plugin Config Dictionary
        File pluginConfigDir = new File("config/%s".formatted(name));
        if (!pluginConfigDir.exists()) pluginConfigDir.mkdir();
        // Config File
        File configFile = new File(path);
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                InputStream inputStream = plugin.getClass().getResourceAsStream("config/%s/config.yml".formatted(name));
                if (inputStream != null) {
                    Files.copy(inputStream, configFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
