package cn.xiaozhou233.xiaoyubot.Plugin;

import cn.xiaozhou233.xiaoyubot.Event.EventBus;
import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {
    private final Logger logger = LoggerFactory.getLogger("PluginManager");
    private final ObjectMapper mapper = new ObjectMapper();
    private final EventBus eventBus = XiaoYuBotX.getEventBus();

    public JavaPlugin loadPlugin(File pluginFile) {
        try(JarFile jarFile = new JarFile(pluginFile)) {
            JarEntry entry = jarFile.getJarEntry("plugin.json");
            if (entry == null)
                throw new RuntimeException("plugin.json not found in " + pluginFile.getName());

            PluginInfo info = mapper.readValue(jarFile.getInputStream(entry), PluginInfo.class);

            PluginClassLoader loader = new PluginClassLoader(pluginFile, this.getClass().getClassLoader());
            Class<?> main = loader.loadClass(info.getMain());
            JavaPlugin plugin = (JavaPlugin) main.getDeclaredConstructor().newInstance();
            plugin.init(info, eventBus);
            plugin.onEnable();

            return plugin;
        } catch (ClassNotFoundException e){
            logger.error("Class not found in plugin {} :", pluginFile.getName(), e);
            throw new RuntimeException(e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
