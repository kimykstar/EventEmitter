package components.core;


import annotations.Event;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;

public class EventProgramEnvorinment {

    public static void init(EmitterManager manager) {
        Iterator<Object> it = manager.iterator();
        File dir = new File("src/events");
        File[] files = dir.listFiles();
        System.out.println(Arrays.toString(files));
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
