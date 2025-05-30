package cn.xiaozhou233.xiaoyubot.Event;

import onebot11.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EventBus {
    private static final Logger logger = LoggerFactory.getLogger("EventBus");
    private final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listeners = new HashMap<>();

    public <E extends Event> void register(Class<E> type, EventListener<E> listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(listener);
        logger.debug("Register listener {} for event {}", listener, type);
    }

    public <E extends Event> void unregister(Class<E> type, EventListener<E> listener) {
        List<EventListener<? extends Event>> list = listeners.get(type);
        if (list != null) {
            list.remove(listener);
            if (list.isEmpty()) {
                listeners.remove(type);
            }
        }
        logger.debug("Unregister listener {} for event {}", listener, type);
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> void post(E event) {
        logger.debug("Posting Event: " + event.getClass().getSimpleName());
        Class<? extends Event> type = event.getClass();
        List<EventListener<? extends Event>> list = listeners.get(type);
        if (list != null) {
            for (EventListener<? extends Event> rawListener : list) {
                ((EventListener<E>) rawListener).onEvent(event);
            }
        }
    }
}
