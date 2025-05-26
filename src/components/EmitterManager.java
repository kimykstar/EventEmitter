package components;

import annotations.Emitter;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class EmitterManager {
    Map<Object, EventEmitter> emitters = new HashMap();

    public void addEmitter(Object obj) {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Emitter.class)) return;
        emitters.put(obj, new EventEmitter());
    }

    public void removeEmitter(String name) {
        emitters.remove(name);
    }

    public void emit(Object obj, String eventName) throws InvocationTargetException, IllegalAccessException {
        EventEmitter emitter = emitters.get(obj);
        emitter.emit(obj, eventName);
    }

    // emitter들을 반환한다.
    public Iterator<Object> iterator() {
        return emitters.keySet().iterator();
    }

    public EventEmitter getEmitter(Object obj) {
        return emitters.get(obj);
    }
}
