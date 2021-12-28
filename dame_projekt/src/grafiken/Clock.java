package grafiken;

import javax.swing.*;

public class Clock {
    private JLabel label;
    private ClockTest clock = new ClockTest(600);
    private ClockTest clock1 = new ClockTest(600);
    int x = 0;

    public Clock (JLabel label) {
        this.label = label;
        reset ();
    } // Clock

    public void update ( ) {
        label.setText (String.valueOf(clock.getCount()) + "  :  " + String.valueOf(clock1.getCount()));
    } // update

    public void reset ( ) {
        clock.setCount(600);
        clock1.setCount(600);
        update ();
    } // reset

    public void switchClock() {
        if (x == 0) {
            clock1.stopClock();
            clock.startClock();
            x = 1;
        }else{
            clock.stopClock();
            clock1.startClock();
            x = 0;
        }
    }


} // Clock
