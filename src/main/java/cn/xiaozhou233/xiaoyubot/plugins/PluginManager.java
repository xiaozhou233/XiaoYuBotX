package cn.xiaozhou233.xiaoyubot.plugins;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginManager {
    private static final Logger logger = LoggerFactory.getLogger("PluginManager");
    private static final List<Plugin> plugins = new ArrayList<>();
    private static final HashMap<Plugin, Logger> loggers = new HashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper();
    private final String pluginPath = "plugins";

    public void loadPlugins() {
        File pluginDir = new File(pluginPath);
        if (!pluginDir.exists()) init();
        for (File file : Objects.requireNonNull(pluginDir.listFiles())) {
            // Pass Directories
            if (file.isDirectory()) continue;
            if (file.getName().endsWith(".jar")) {
                loadPlugin(file);
            }
        }
    }

    public void loadPlugin(File file) {
        try {
            PluginMeta meta = getPluginMeta(file);
            URL[] urls = {file.toURI().toURL()};
            URLClassLoader loader = new URLClassLoader(urls);
            Class<?> clazz = loader.loadClass(meta.main);
            Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();
            loggers.put(plugin, LoggerFactory.getLogger(meta.name));
            plugin.onEnable();
            plugins.add(plugin);
        } catch (Exception e) {
            logger.error("Failed to load plugin: {}", file.getName(), e);
            throw new RuntimeException(e);
        }
    }

    public void loadPlugin(Plugin plugin, String name, String version, Class<?> main) {
        try {
            PluginMeta meta = new PluginMeta(name, version, main.getName());
            plugin.onEnable();
            plugins.add(plugin);
        } catch (Exception e) {
            logger.error("Failed to load plugin: {} (Error:1)", name, e);
            throw new RuntimeException(e);
        }
    }

    public void unloadPlugin(Plugin plugin) {
        plugin.onDisable();
        plugins.remove(plugin);
    }

    public static List<Plugin> getPlugins() {
        return plugins;
    }

    private PluginMeta getPluginMeta(File file) {
        try (JarFile jarFile = new JarFile(file)) {
            JarEntry entry = jarFile.getJarEntry("plugin.json");
            if (entry == null) throw new RuntimeException("Plugin meta not found in " + file.getName());
            try (InputStream inputStream = jarFile.getInputStream(entry)) {
                return mapper.readValue(inputStream, PluginMeta.class);
            }
        } catch (IOException e) {
            logger.error("Failed to read plugin meta: {}", file.getName(), e);
            throw new RuntimeException(e);
        }
    }


    private void init(){
        File pluginDir = new File(pluginPath);
        if (pluginDir.mkdir()) {
            logger.info("Plugin directory created.");
        } else {
            logger.error("Failed to create plugin directory.");
            throw new RuntimeException("Cannot create plugin directory! Maybe it's already exist or not write permission.");
        }
    }

    public static Logger getLogger(Plugin plugin){
        Logger pluginLogger = loggers.get(plugin);
        assert pluginLogger != null;
        return pluginLogger;
    }

    public record PluginMeta(String name, String version, String main) {
    }
}
