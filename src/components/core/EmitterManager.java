package components.core;

import annotations.Emitter;
import annotations.Event;
import components.bean.ClassPathScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EmitterManager {
    Map<String, EventEmitter> emitters = new HashMap<>();
    Map<String, Object> instances = new HashMap<>();

    public void addEmitter(Object obj) {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Emitter.class)) return;
        String className = clazz.getName().split("\\.")[1];
        emitters.put(className, new EventEmitter());
        instances.put(className, obj);
    }

    public void removeEmitter(String name) {
        emitters.remove(name);
    }

    public void emit(String emitterName, String eventName) throws InvocationTargetException, IllegalAccessException {
        EventEmitter emitter = emitters.get(emitterName);
        emitter.emit(instances.get(emitterName), eventName);
    }

    public Iterator<String> iterator() {
        return emitters.keySet().iterator();
    }

    public void initManager(ClassPathScanner classScanner) {
        Object[] objs = classScanner.getEventInstances();
        for (Object obj : objs) {
            addEmitter(obj);
        }

        Iterator<String> it = iterator();

        while (it.hasNext()) {
            String emitterName = it.next();
            EventEmitter emitter = emitters.get(emitterName);
            Object obj = instances.get(emitterName);
            Class<?> clazz = obj.getClass();
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Event.class)) {
                    String eventName = method.getAnnotation(Event.class).value();
                    emitter.on(eventName, method);
                }
            }
        }
    }
}
