import java.util.ArrayList;
import java.util.List;

public class Broker {
    List<Subscriber> subscribers;

    public Broker() {
        subscribers = new ArrayList<>();
    }

    public void notifyEvent(String event) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(event);
        }
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

}
