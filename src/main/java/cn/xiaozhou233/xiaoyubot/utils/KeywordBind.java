package cn.xiaozhou233.xiaoyubot.utils;

import cn.xiaozhou233.xiaoyubot.onebotapi.event.message.GroupMessageEvent;
import cn.xiaozhou233.xiaoyubot.plugin;

import java.util.HashMap;

public class KeywordBind {
    private static HashMap<String, plugin> bind = new HashMap<>();
    public static void bind(plugin pluginInstance, String keyword){
        if (bind.containsKey(keyword))
            System.out.println("[Warn] KeywordBind Manager found a keyword conflict: " + keyword + "something maybe wrong");
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
