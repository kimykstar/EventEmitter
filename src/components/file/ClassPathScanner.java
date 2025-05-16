package components.file;

import java.io.File;
import java.util.Arrays;

public class ClassPathScanner {
    private static final String CLASS_PATH = "src/events";

    public ClassPathScanner() {

    }

    public String[] getClasses() {
        File dir = new File(CLASS_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".class"));
        // ToDo: files가 Null인 경우 예외 처리
        return Arrays.stream(files).map(File::getName).toArray(String[]::new);
    }
}
