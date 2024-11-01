package cn.xiaozhou233.xiaoyubot.utils;

import cn.xiaozhou233.xiaoyubot.plugin;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginManager {

    private static final List<plugin> plugins = new ArrayList<>();
    private static final String PLUGIN_DIR = "plugins";

    public static void loadPlugins() {
        File folder = new File(PLUGIN_DIR);
        if (!folder.exists() && !folder.mkdir()) {
            System.err.println("[ERROR] [PluginManager] Failed to create plugin directory.");
            return;
        }

        File[] pluginFiles = folder.listFiles((dir, name) -> name.endsWith(".jar"));
        if (pluginFiles == null || pluginFiles.length == 0) {
            System.out.println("[INFO] [PluginManager] No plugins found, skipping load.");
            return;
        }

        for (File pluginFile : pluginFiles) {
            loadPlugin(pluginFile);
        }
    }

    private static void loadPlugin(File pluginFile) {
        try (JarFile jarFile = new JarFile(pluginFile)) {
            JarEntry configEntry = jarFile.getJarEntry("plugin.toml");
            if (configEntry == null) {
                System.out.printf("[WARN] [PluginManager] Plugin [%s] missing plugin.toml configuration.%n", pluginFile.getName());
                return;
            }

            PluginMetadata metadata = parsePluginMetadata(jarFile, configEntry, pluginFile);
            if (metadata == null) return;

            System.out.printf("[INFO] [PluginManager] Loading plugin [%s] version [%s].%n", metadata.name, metadata.version);
            instantiateAndEnablePlugin(pluginFile, metadata);
        } catch (Exception e) {
            System.err.printf("[ERROR] [PluginManager] Failed to load plugin [%s]: %s%n", pluginFile.getName(), e.getMessage());
            e.printStackTrace();
        }
    }

    private static PluginMetadata parsePluginMetadata(JarFile jarFile, JarEntry configEntry, File pluginFile) {
        try (InputStream is = jarFile.getInputStream(configEntry)) {
            TomlParseResult result = Toml.parse(is);
            String name = result.getString("PluginName");
            String version = result.getString("PluginVersion");
            String mainClass = result.getString("MainClass");

            if (name == null || name.isEmpty()) {
                System.out.printf("[WARN] [PluginManager] Plugin [%s] is missing PluginName in plugin.toml.%n", pluginFile.getName());
            }
            if (version == null || version.isEmpty()) {
                System.out.printf("[WARN] [PluginManager] Plugin [%s] is missing PluginVersion in plugin.toml.%n", pluginFile.getName());
            }
            if (mainClass == null || mainClass.isEmpty()) {
                System.out.printf("[ERROR] [PluginManager] Plugin [%s] is missing MainClass in plugin.toml, skipping.%n", pluginFile.getName());
                return null;
            }

            return new PluginMetadata(name, version, mainClass);
        } catch (Exception e) {
            System.err.printf("[ERROR] [PluginManager] Error parsing plugin.toml for [%s]: %s%n", pluginFile.getName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static void instantiateAndEnablePlugin(File pluginFile, PluginMetadata metadata) {
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{pluginFile.toURI().toURL()}, PluginManager.class.getClassLoader())) {
            plugin pluginInstance = classLoader.loadClass(metadata.mainClass)
                    .asSubclass(plugin.class)
                    .getDeclaredConstructor()
                    .newInstance();
            pluginInstance.onEnable();
            plugins.add(pluginInstance);
            System.out.printf("[INFO] [PluginManager] Successfully loaded plugin [%s] version [%s].%n", metadata.name, metadata.version);
        } catch (Exception e) {
            System.err.printf("[ERROR] [PluginManager] Failed to initialize plugin [%s]: %s%n", metadata.name, e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<plugin> getPlugins() {
        return new ArrayList<>(plugins);
    }

    private static class PluginMetadata {
        final String name;
        final String version;
        final String mainClass;

        PluginMetadata(String name, String version, String mainClass) {
            this.name = name;
            this.version = version;
            this.mainClass = mainClass;
        }
    }
}
