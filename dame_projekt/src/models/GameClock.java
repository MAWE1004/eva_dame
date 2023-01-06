package models;

import controller.ErgebnisMVC;

import javax.swing.*;

public class GameClock {

    private JLabel label;
    private long startTime;
    private long countTime;
    private long elapsedTime;
    private boolean toggle;

    public GameClock(JLabel label, long countTime) {
        this.label = label;
        this.countTime = countTime;
        reset();
    }

    public void update() {
        elapsedTime = startTime - System.currentTimeMillis();
        long min = elapsedTime / 60000;
        long seconds = elapsedTime % 60000 / 1000;
        String prefix;
        if(seconds < 10)
            prefix = "0";
        else
            prefix = "";
        label.setText(min + "." + prefix + seconds);
        if(label.getText().equals("0.00")){
            toggle = true;
        }


    }

    public boolean getToggle(){
        return toggle;
    }


    public void pause() {
        countTime = elapsedTime;
    }

    public void continueClock() {
        reset();
    }

    public void reset(){
        startTime = System.currentTimeMillis() + countTime;
        update();
    }
}
