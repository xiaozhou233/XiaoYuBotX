package cn.xiaozhou233.xiaoyubot.Event;

import onebot11.event.Event;

public interface EventListener<E extends Event> {
    void onEvent(E event);
}

