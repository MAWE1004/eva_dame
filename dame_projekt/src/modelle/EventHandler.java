package modelle;

import grafiken.Clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    private Clock clock;
    private Clock clock1;
    private Ticker ticker;
    private Ticker ticker1;
    public EventHandler(Clock clock, Clock clock1) {
        this.clock = clock;
        this.clock1 = clock1;
    } // EventHandler
    public void actionPerformed (ActionEvent e) {
        String s = e.getActionCommand ();
        if (s.equals ("Start")) {

            if (ticker == null) {
                clock.reset ( );
            //    clock.switchClock();
                ticker = new Ticker (clock);

            }
            if (ticker1 == null) {
                clock1.reset ( );
                ticker1 = new Ticker (clock1);
            }
        }
        else if (s.equals ("Stopp") ) {
            if (ticker != null) {
                ticker.interrupt ( );
                ticker = null;
            }
            if (ticker1 != null) {
                ticker1.interrupt ( );
                ticker1 = null;
            }
        }
        else if (s.equals ("Null") ) {
            clock.reset ( );
            clock1.reset ( );
        }
        else if(s.equals("Ende")) {
            System.exit (0);
        }
        else if(s.equals("Switch")){
            clock.switchClock();
        }
    } // actionPerformed
} // EventHandler
