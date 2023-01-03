package clock;

import java.awt.*;

public class Ticker extends Thread{
    private final static long UPDATE_INTERVAL = 10;
    private UpdateRequest updateReq;

    public Ticker(Clock clock){
        updateReq = new UpdateRequest(clock);
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
