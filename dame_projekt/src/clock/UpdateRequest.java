package clock;

public class UpdateRequest implements Runnable{
    private Clock clock;

    public UpdateRequest(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void run() {
        clock.update();
    }
}
