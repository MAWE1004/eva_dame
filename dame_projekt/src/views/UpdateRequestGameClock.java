package views;


import models.GameClock;

public class UpdateRequestGameClock implements Runnable{
    private GameClock clock;

    public UpdateRequestGameClock(GameClock clock) {
        this.clock = clock;
    }

    @Override
    public void run() {
        clock.update();
    }
}
