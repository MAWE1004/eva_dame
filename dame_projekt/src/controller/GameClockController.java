package controller;

import models.GameClock;
import views.TickerGameClock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClockController implements ActionListener {
    private GameClock clock;
    private TickerGameClock ticker;

    public GameClockController(GameClock clock) {
        this.clock = clock;
        clock.reset();
        ticker = new TickerGameClock(clock);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Start")){
            if(ticker == null){
                clock.reset();
                ticker = new TickerGameClock(clock);
            }
        }
        else if (s.equals ("Stopp") ) {
            if (ticker != null) {
                ticker.interrupt ( );
                ticker = null;
            }
        }
        else if (s.equals ("Null") ) {
            clock.reset ( );
        }
        else if(s.equals("Ende")) {
            System.exit (0);
        }
    }
}
