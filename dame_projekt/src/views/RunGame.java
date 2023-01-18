package views;

import controller.GameAndGameInfoMVC;

import java.awt.*;
import java.io.IOException;

public class RunGame extends Thread {

    private GameAndGameInfoMVC game;
    private long timeInMin;
    private String schwarz;
    private String weiß;
    private boolean turn;
    private Color farbe;
    private String adr;

    public RunGame(long timeInMin, String schwarz, String weiß, boolean turn, Color farbe, String adr) {
        this.game = game;
        this.timeInMin = timeInMin;
        this.schwarz = schwarz;
        this.weiß = weiß;
        this.turn = turn;
        this.farbe = farbe;
        this.adr = adr;
    }

    public GameAndGameInfoMVC getGame() {
        return game;
    }

    @Override
    public void run() {
        try {
            game = new GameAndGameInfoMVC(timeInMin, schwarz, weiß, turn, farbe, adr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
