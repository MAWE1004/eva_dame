package views;

import clock.Clock;
import clock.UpdateRequest;
import models.GameClock;

import java.awt.*;

public class TickerGameClock extends Thread{
    private final static long UPDATE_INTERVAL = 10;
    private UpdateRequestGameClock updateReq;

    public TickerGameClock(GameClock clock){
        updateReq = new UpdateRequestGameClock(clock);
        start();
    }

    public void run(){
        try {
            while(!isInterrupted()){
                EventQueue.invokeLater(updateReq);
                Thread.sleep(UPDATE_INTERVAL);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
