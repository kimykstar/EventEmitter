public class Subscriber {
    private int subscriberId;

    public Subscriber(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public void update(String event) {
        System.out.println(event + subscriberId);
    }
}
