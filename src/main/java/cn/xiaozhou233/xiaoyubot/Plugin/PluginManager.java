package cn.xiaozhou233.xiaoyubot.Plugin;

import cn.xiaozhou233.xiaoyubot.Event.EventBus;
import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PluginManager {
    private final Logger logger = LoggerFactory.getLogger("PluginManager");
    private final List<JavaPlugin> plugins = new ArrayList<>();
    private final PluginLoader pluginLoader = new PluginLoader();

    public void loadPlugin(File pluginFile){
        JavaPlugin plugin = pluginLoader.loadPlugin(pluginFile);
        plugins.add(plugin);
    }

    public void loadPlugins(){
        File pluginDir = new File("./plugins");
        for (File file : pluginDir.listFiles()) {
            if (!file.getName().endsWith(".jar")) return;
            JavaPlugin plugin = pluginLoader.loadPlugin(file);
            plugins.add(plugin);
        }
    }

    public List<JavaPlugin> getPlugins() {
        return plugins;
    }

    public PluginLoader getPluginLoader() {
        return pluginLoader;
    }
}
