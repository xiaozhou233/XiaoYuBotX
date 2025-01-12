package cn.xiaozhou233.xiaoyubot.plugins;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginsManager {
    private static final ArrayList<PluginInterface> plugins = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private static final Logger logger = LoggerFactory.getLogger("PluginManager");

    public static void loadPlugins() {
        File directory = new File("plugins");
        if(!directory.exists()) directory.mkdir();
        for(File file : Objects.requireNonNull(directory.listFiles())){
            if(file.isFile() && file.getName().endsWith(".jar")){
                try {
                    loadPlugin(file);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void loadPlugin(File file) throws IOException {
        try (JarFile jarFile = new JarFile(file)) {
            JarEntry entry = jarFile.getJarEntry("plugin.yml");
            MetaInfo info = objectMapper.readValue(jarFile.getInputStream(entry), MetaInfo.class);
            if(info.name != null && info.version != null && info.main != null){
                logger.info("Loading plugin {} v{}", info.name, info.version);
                try (URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()})) {
                    Class<?> pluginClass = classLoader.loadClass(info.main);
                    PluginInterface plugin = (PluginInterface) pluginClass.getDeclaredConstructor().newInstance();
                    plugin.Bot = new Bot(info.name);
                    plugin.onEnable();
                    plugins.add(plugin);
                    logger.info("Plugin {} {} loaded successfully", info.name, info.version);
                } catch (Exception e){
                    logger.error("Failed to load plugin {}", file.getName(), e);
                    throw new RuntimeException(e);
                }
            } else {
                logger.warn("File {} is not a valid plugin", file.getName());
            }
        }
    }

    public static void disablePlugin(String name){
        for (PluginInterface plugin : plugins){
            if (plugin.Bot.name.equals(name)){
                plugin.onDisable();
                plugins.remove(plugin);
                logger.info("Plugin {} disabled successfully", name);
                return;
            }
        }
        logger.warn("Plugin {} not found", name);
    }


    private static class MetaInfo{
        private String name;
        private String version;
        private String main;
    }
}
