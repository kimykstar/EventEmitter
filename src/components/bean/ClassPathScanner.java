package components.bean;

import components.exception.EventPackageNotFoundException;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ClassPathScanner {
    private static final String CLASS_PATH = "src/events";

    public ClassPathScanner() {

    }

    private String[] getClassNames() throws EventPackageNotFoundException {
        File dir = new File(CLASS_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".java"));
        if (files == null) {
            throw new EventPackageNotFoundException();
        }
        return Arrays.stream(files).map(file -> {
            String name = file.getName();
            return name.split("\\.")[0];
        }).toArray(String[]::new);
    }

    public Object[] getEventInstances() {
        try {
            Object[] objs;
            String[] classNames = getClassNames();
            objs = new Object[classNames.length];
            Class<?>[] clazzes = new Class<?>[classNames.length];
            for (int i = 0; i < classNames.length; i++) {
                clazzes[i] = Class.forName("events.%s".formatted(classNames[i]));
                objs[i] = clazzes[i].getDeclaredConstructor().newInstance();
            }
            return objs;
        } catch (EventPackageNotFoundException | ClassNotFoundException | NoSuchMethodException e ) {
            System.out.println("Evnet Class Not Found");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
