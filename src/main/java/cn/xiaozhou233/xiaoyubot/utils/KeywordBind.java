package cn.xiaozhou233.xiaoyubot.utils;

import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.GroupMessageEvent;
import cn.xiaozhou233.xiaoyubot.plugin;
import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import java.util.HashMap;

public class KeywordBind {
    private static final HashMap<String, plugin> bind = new HashMap<>();
    private static final TaggedLogger logger = Logger.tag("KeywordBind");

    public static void bind(plugin pluginInstance, String keyword){
        if (bind.containsKey(keyword))
            logger.warn("Keyword {} is already bind! something wrong maybe happen.", keyword);
        bind.put(keyword, pluginInstance);
    }

    public static void onAtBotEvent(GroupMessageEvent event){
        if(bind.containsKey(event.getText(true))){
            // XXX Fixed when keyword conflicts occur
            for(String keyword : bind.keySet()){
                if(event.getText(true).contains(keyword)){
                    bind.get(keyword).onTriggerKeyword(keyword, event);
                }
            }
        }
    }
}
