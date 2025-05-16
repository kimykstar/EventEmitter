package components.core;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventEmitter {
    Map<String, Method> events = new HashMap<>();

    public void on(String eventName, Method callBack) {
        events.put(eventName, callBack);
    }

    public void emit(Object obj, String eventName) throws InvocationTargetException, IllegalAccessException {
        if(events.containsKey(eventName)) {
            events.get(eventName).invoke(obj);
        }
    }
}
