package views;

import clock.Clock;
import clock.UpdateRequest;
import controller.ErgebnisMVC;
import controller.menu.MenuMVC;
import models.GameClock;

import java.awt.*;

public class TickerGameClock extends Thread{
    private final static long UPDATE_INTERVAL = 10;
    private UpdateRequestGameClock updateReq;
    private GameClock clock;

    public TickerGameClock(GameClock clock){
        this.clock = clock;
        updateReq = new UpdateRequestGameClock(clock);
        start();
    }

    public void run(){
        try {
            while(!isInterrupted()){
                EventQueue.invokeLater(updateReq);
                Thread.sleep(UPDATE_INTERVAL);

                if (clock.getToggle() == true){
                    interrupt();
                    new ErgebnisMVC("Zeit ist überschritten, du hast verloren!");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
