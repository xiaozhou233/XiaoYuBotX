package cn.xiaozhou233.xiaoyubot.command;

import cn.xiaozhou233.xiaoyubot.XiaoYuBotX;
import cn.xiaozhou233.xiaoyubot.onebot.event.message.GroupMessage;
import cn.xiaozhou233.xiaoyubot.onebot.event.message.PrivateMessage;
import cn.xiaozhou233.xiaoyubot.plugins.Plugin;
import cn.xiaozhou233.xiaoyubot.plugins.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;

public class CommandParser extends Plugin{
    private static final Logger logger = LoggerFactory.getLogger("CommandParser");
    private static final HashMap<String, Plugin> cmdMap = new HashMap<>();
    private static String prefix = XiaoYuBotX.getBotConfig().get("command_prefix").asText();

    public CommandParser() {
        XiaoYuBotX.getPluginManager().loadPlugin(this, "CommandParser", "1.0", this.getClass());
    }

    public static void register(String command, Plugin plugin) {
        cmdMap.put(command, plugin);
        logger.info("Registered command: {} for plugin {}", command, plugin.getClass().getName());
    }

    @Override
    public void onGroupMessage(GroupMessage event) {
        if (event.getRawMessage().replace(" ", "").startsWith(prefix)){
            cmdMap.forEach((cmd, plugin) -> {
                plugin.onCommand(event, cmd);
            });
        }
    }


    @Override
    public void onPrivateMessage(PrivateMessage event) {
        if (event.getRawMessage().replace(" ", "").startsWith(prefix)){
            cmdMap.forEach((cmd, plugin) -> {
                plugin.onCommand(event, cmd);
            });
        }
    }

    @Override
    public void onEnable() {
        logger.info("CommandParser enabled!");
    }

    @Override
    public void onDisable() {
        logger.info("CommandParser disabled!");
    }
}
