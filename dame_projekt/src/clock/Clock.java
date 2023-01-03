package clock;

import javax.swing.*;

public class Clock {

    private JLabel label;
    private long startTime;

    public Clock(JLabel label) {
        this.label = label;
        reset();
    }

    public void update() {
        long elapsedTime = startTime - System.currentTimeMillis();
        long seconds = elapsedTime / 1000;
        long milliSecs = elapsedTime % 1000;
        String prefix;
        if(milliSecs < 10)
            prefix = "00";
        else if(milliSecs < 100)
            prefix = "0";
        else
            prefix = "";
        label.setText(seconds + "." + prefix + milliSecs);
        if(label.getText().equals("0.000"))
            reset();

    }

    public void reset(){
        startTime = System.currentTimeMillis() + 5000;
        update();
    }
}
