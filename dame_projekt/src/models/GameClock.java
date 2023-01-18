package models;

import controller.GameAndGameInfoMVC;
import models.thread.TickerGameClock;

import javax.swing.*;

public class GameClock {

    private JLabel label;
    private long startTime;
    private long countTime;
    private long elapsedTime;
    private boolean toggle;
    private GameAndGameInfoMVC gameAndGameInfoMVC;
    private TickerGameClock tickerGameClock;

    public GameClock(JLabel label, long countTime, GameAndGameInfoMVC gameAndGameInfoMVC) {
        this.label = label;
        this.countTime = countTime;
        this.gameAndGameInfoMVC = gameAndGameInfoMVC;
        gameAndGameInfoMVC.setClock(this);
        tickerGameClock = null;
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

    public TickerGameClock getTickerGameClock() {
        return tickerGameClock;
    }

    public void setTickerGameClock(TickerGameClock tickerGameClock) {
        this.tickerGameClock = tickerGameClock;
    }

    public GameAndGameInfoMVC getGameAndGameInfoMVC() {
        return gameAndGameInfoMVC;
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
