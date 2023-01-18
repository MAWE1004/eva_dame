package views;


import models.GameClock;

import java.awt.*;
import java.io.IOException;

public class TickerGameClock extends Thread{
    private final static long UPDATE_INTERVAL = 10;
    private UpdateRequestGameClock updateReq;
    private GameClock clock;

    public TickerGameClock(GameClock clock){
        this.clock = clock;
        updateReq = new UpdateRequestGameClock(clock);
        clock.setTickerGameClock(this);
        start();
    }


    public void run(){
        try {
            while(!isInterrupted()){
                EventQueue.invokeLater(updateReq);
                Thread.sleep(UPDATE_INTERVAL);

                if (clock.getToggle() == true){
                    clock.getGameAndGameInfoMVC().getBrett().sendGameOver((byte) 0);
                    interrupt();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
