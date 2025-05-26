public class Publisher {
    Broker broker;

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public void notifyEvent(String event) {
        broker.notifyEvent(event);
    }
}
