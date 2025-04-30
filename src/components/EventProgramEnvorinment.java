package components;


import annotations.Event;

import java.lang.reflect.Method;
import java.util.Iterator;

public class EventProgramEnvorinment {

    public static void init(EmitterManager manager) {
        Iterator<Object> it = manager.iterator();

        while (it.hasNext()) {
            Object emitterInstance = it.next();
            EventEmitter emitter = manager.getEmitter(emitterInstance); // 애너테이션 찾기
            for (Method method : emitterInstance.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Event.class)) {
                    String eventName = method.getAnnotation(Event.class).value();
                    emitter.on(eventName, method);
                }
            }
        }
    }


}
