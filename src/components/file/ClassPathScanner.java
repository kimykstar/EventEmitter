package components.file;

import components.exception.EventPackageNotFoundException;

import java.io.File;
import java.util.Arrays;

public class ClassPathScanner {
    private static final String CLASS_PATH = "src/events";

    public ClassPathScanner() {

    }

    public String[] getClassNames() throws EventPackageNotFoundException {
        File dir = new File(CLASS_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".class"));
        if (files == null) {
            throw new EventPackageNotFoundException();
        }
        return Arrays.stream(files).map(File::getName).toArray(String[]::new);
    }
}
