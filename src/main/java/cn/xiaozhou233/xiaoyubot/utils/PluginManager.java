package cn.xiaozhou233.xiaoyubot.utils;

import cn.xiaozhou233.xiaoyubot.plugin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class PluginManager {
    private static final List<plugin> plugins = new ArrayList<>();
    private static final String PLUGIN_PATH = "plugins";
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private static final TaggedLogger logger = Logger.tag("PluginManager");


    public static void loadPlugins() {
        // check plugin folder exist
        File pluginFolder = new File(PLUGIN_PATH);
        if (!pluginFolder.exists()) pluginFolder.mkdir();
        // get jar files
        File[] pluginFiles = pluginFolder.listFiles((dir, name) -> name.endsWith(".jar"));
        if (pluginFiles == null) return;

        for (File pluginFile : pluginFiles) {
            try {
                PluginMetadata metadata = getPluginMetadata(pluginFile);
                if (metadata == null) continue;

                URL url = pluginFile.toURI().toURL();
                try(URLClassLoader classLoader = new URLClassLoader(new URL[]{url})) {
                    Class<?> pluginClass = classLoader.loadClass(metadata.mainClass);
                    plugin plugin = (cn.xiaozhou233.xiaoyubot.plugin) pluginClass.getDeclaredConstructor().newInstance();
                    plugin.onEnable();
                    plugins.add(plugin);
                } catch (Exception e) {
                    logger.error("Error loading plugin {}", pluginFile.getName());
                    logger.trace(e);
                }
            } catch (Exception e) {
                logger.error("Error loading plugin {}", pluginFile.getName());
                logger.trace(e);
            }
        }
    }

    private static PluginMetadata getPluginMetadata(File pluginFile) {
        try (JarFile jarFile = new JarFile(pluginFile)) {
            JarEntry entry = jarFile.getJarEntry("plugin.yml");
            if (entry == null) {
                logger.warn("Missing plugin.yml in plugin " + pluginFile.getName());
                return null;
            }
            try (InputStream input = jarFile.getInputStream(entry)) {
                JsonNode result = objectMapper.readTree(input);
                String name = result.get("PluginName").asText();
                String version = result.get("PluginVersion").asText();
                String mainClass = result.get("MainClass").asText();

                if (name != null && version != null && mainClass != null) {
                    return new PluginMetadata(name, version, mainClass);
                } else {
                    logger.warn("Missing required fields in plugin.yml of plugin " + pluginFile.getName());
                }
            }
        } catch (Exception e) {
            logger.error("Error reading plugin.yml of plugin " + pluginFile.getName());
            logger.trace(e);
        }
        return null;
    }



    public static List<plugin> getPlugins() {
        return plugins;
    }


    private record PluginMetadata(String name, String version, String mainClass) {
    }

}
