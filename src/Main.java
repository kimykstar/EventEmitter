import components.EmitterManager;
import components.EventProgramEnvorinment;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client();
        // 원래는 동적으로 클래스들을 읽어서 해당 클래스에 해당하는 EventEmitter를 각각 생성해야함
        EmitterManager manager = new EmitterManager();
        manager.addEmitter(server);
        manager.addEmitter(client);

        try{
            EventProgramEnvorinment.init(manager);
            manager.emit(server, "receive");
            manager.emit(client, "receive");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
