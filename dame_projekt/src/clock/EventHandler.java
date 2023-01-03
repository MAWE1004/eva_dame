package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    private Clock clock;
    private Ticker ticker;

    public EventHandler(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Start")){
            if(ticker == null){
                clock.reset();
                ticker = new Ticker(clock);
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
