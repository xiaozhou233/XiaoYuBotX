package cn.xiaozhou233.xiaoyubot.config;

import cn.xiaozhou233.xiaoyubot.plugins.Plugin;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class ConfigManager {
    private static final Yaml yaml = new Yaml();
    private final String path;
    private final Plugin plugin;
    private final String name;
    private final File file;
    private static HashMap<String, Object> config = new HashMap<>();
    public ConfigManager(Plugin plugin, String name) {
        path = "config/%s/config.yml".formatted(name);
        this.plugin = plugin;
        this.name = name;
        file = new File(path);
        if (!file.exists()) initConfigPath();
        try(InputStream inputStream = Files.newInputStream(file.toPath())) {
            config = yaml.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object get(String key) {
        return config.getOrDefault(key, null);
    }

    public void set(String key, Object value) {
        config.put(key, value);
    }

    public void saveConfig() throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            yaml.dump(config, writer);
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
