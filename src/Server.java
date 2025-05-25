import annotations.Emitter;
import annotations.Event;

@Emitter
public class Server {

    @Event("receive")
    public void receive() {
        System.out.println("server receive");
    }
}
