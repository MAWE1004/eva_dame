package grafiken;

import javax.swing.*;

public class Clock {
    private JLabel label;
    private ClockTest clock;
    private ClockTest clock1;
    boolean switchClock = false;

    public Clock (JLabel label) {
        clock = new ClockTest(600);
        clock1 = new ClockTest(600);
        this.label = label;
        reset();
    } // Clock

    public void update ( ) {
        label.setText ( "Weiß: " + String.valueOf(clock.getCount()) + "  Schwarz: " + String.valueOf(clock1.getCount()));
    } // update

    public void reset ( ) {
        clock.setCount(600);
        clock1.setCount(600);
        clock1.stopClock();
        update ();
    } // reset

    public void switchClock() {
        if (switchClock) {
            clock1.stopClock();
            clock.startClock();
            switchClock = false;
        }else{
            clock.stopClock();
            clock1.startClock();
            switchClock = true;
        }
    }

    public void stopAllClocks(){
        clock.stopClock();
        clock1.stopClock();
    }
} // Clock
