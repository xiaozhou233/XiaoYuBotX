package cn.xiaozhou233.xiaoyubot.Event;

import onebot11.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EventBus {
    private final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listeners = new HashMap<>();

    public <E extends Event> void register(Class<E> type, EventListener<E> listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(listener);
    }

    public <E extends Event> void unregister(Class<E> type, EventListener<E> listener) {
        List<EventListener<? extends Event>> list = listeners.get(type);
        if (list != null) {
            list.remove(listener);
            if (list.isEmpty()) {
                listeners.remove(type);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> void post(E event) {
        Class<? extends Event> type = event.getClass();
        List<EventListener<? extends Event>> list = listeners.get(type);
        if (list != null) {
            for (EventListener<? extends Event> rawListener : list) {
                ((EventListener<E>) rawListener).onEvent(event);
            }
        }
    }
}
