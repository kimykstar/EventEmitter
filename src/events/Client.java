package events;

import annotations.Emitter;
import annotations.Event;

@Emitter
public class Client {

    @Event("receive")
    public void receive() {
        System.out.println("client receive message");
    }
}
