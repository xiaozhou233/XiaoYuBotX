package cn.xiaozhou233.xiaoyubot.utils;

import cn.xiaozhou233.xiaoyubot.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class PluginManager {
    private static List<plugin> plugins = new ArrayList<>();
    private static final String PLUGIN_PATH = "plugins";

    public static void loadPlugins(){
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
                ClassLoader classLoader = new URLClassLoader(new URL[]{url});
                Class<?> pluginClass = classLoader.loadClass(metadata.mainClass);
                plugin plugin = (cn.xiaozhou233.xiaoyubot.plugin) pluginClass.getDeclaredConstructor().newInstance();
                plugin.onEnable();
                plugins.add(plugin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static PluginMetadata getPluginMetadata(File pluginFile) {
        try (JarFile jarFile = new JarFile(pluginFile)) {
            JarEntry entry = jarFile.getJarEntry("plugin.toml");
            if (entry == null) {
                System.out.println("plugin.toml not found in " + pluginFile.getName());
                return null;
            }
            try (InputStream input = jarFile.getInputStream(entry)) {
                TomlParseResult result = Toml.parse(input);
                String name = result.getString("PluginName");
                String version = result.getString("PluginVersion");
                String mainClass = result.getString("MainClass");

                if (name != null && version != null && mainClass != null) {
                    return new PluginMetadata(name, version, mainClass);
                } else {
                    System.out.println("Missing required fields in plugin.toml");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static List<plugin> getPlugins() {
        return plugins;
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

    public static void main(String[] args) {
        loadPlugins();
    }

}
