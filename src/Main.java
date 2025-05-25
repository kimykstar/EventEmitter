import components.bean.ClassPathScanner;
import components.core.EmitterManager;

public class Main {

    public static void main(String[] args) {
        EmitterManager manager = new EmitterManager();
        ClassPathScanner classScanner = new ClassPathScanner();
        manager.initManager(classScanner);
        try{
            manager.emit("Server", "receive");
            manager.emit("Client", "receive");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
