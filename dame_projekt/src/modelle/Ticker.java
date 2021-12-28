package modelle;

import grafiken.Clock;

import java.awt.*;

class Ticker extends Thread {
    private final static long UPDATE_INTERVAL = 10; // Milliseconds
    private UpdateRequest updateReq;
    public Ticker (Clock clock) {
        updateReq = new UpdateRequest (clock);
        start ( );
    } // Ticker
    public void run ( ) {
        try {
            while(!isInterrupted ( ) ) {
                EventQueue.invokeLater(updateReq);
                Thread.sleep (UPDATE_INTERVAL);
            }
        }
        catch(InterruptedException e) { }
    } // run
} // Ticker