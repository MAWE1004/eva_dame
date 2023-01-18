package models.thread;

import controller.GameAndGameInfoMVC;

public class GameAndGameInfoThread extends Thread{
    private GameAndGameInfoMVC game;

    public GameAndGameInfoThread(GameAndGameInfoMVC game) {
        this.game = game;
        start();
    }

    public void run(){
        while(!isInterrupted()){
        }
    }
}
