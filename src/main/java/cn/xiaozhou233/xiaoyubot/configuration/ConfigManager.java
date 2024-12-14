package cn.xiaozhou233.xiaoyubot.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import java.io.*;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ConfigManager {
    private static HashMap<String, ConfigManager> configMap = new HashMap<>();
    private static final TaggedLogger logger = Logger.tag("ConfigManager");
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private final String name;
    private final File pluginFile;
    private final File configFile;
    private JsonNode result;
    public ConfigManager(String name, File pluginFile) {
        this.name = name;
        this.pluginFile = pluginFile;
        this.configFile = new File("plugins/%s/config.yml".formatted(name));

        if (configMap == null) {
            configMap = new HashMap<>();
        }

        if (configMap.containsKey(name)) {
            logger.error("ConfigManager for plugin {} already exists", name);
            return;
        }

        if (!configFile.getParentFile().exists() && !configFile.exists()){
            boolean status = copyConfig();
            // if not config
            if (!status) {
                return;
            }
        }

        try {
            result = objectMapper.readTree(configFile);
        } catch (IOException e) {
            logger.error("Failed to read config file for plugin {}", name, e);
        }
        // After successful creation, add it to the configMap
        configMap.put(name, this);
    }


    private boolean copyConfig() {
        try (JarFile jarFile = new JarFile(pluginFile)) {
            JarEntry entry = jarFile.getJarEntry("config.yml");
            if (entry == null) {
                return false;
            }

            if (!configFile.getParentFile().exists()) {
                if (!configFile.getParentFile().mkdirs()) {
                    logger.error("Failed to create directory for config file {}", configFile.getParentFile());
                    return false;
                }
            }

            try (InputStream inputStream = jarFile.getInputStream(entry);
                 OutputStream outputStream = new FileOutputStream(configFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }catch (Exception e){
            logger.error(e, "Failed to copy config file for plugin {}", name);
        }
        return true;
    }


    public String getString(String key) {
        assert result.get(key).asText() != null;
        return result.get(key).asText();
    }

    public int getInt(String key) {
        return result.get(key).asInt();
    }

    public boolean getBoolean(String key) {
        return result.get(key).asBoolean();
    }

    public Object get(String key){
        return result.get(key);
    }

    public static ConfigManager getConfigManager(String name) {
        if (configMap.containsKey(name)) {
            return configMap.get(name);
        }
        logger.warn("ConfigManager for plugin {} does not exist", name);
        return null;
    }

}
