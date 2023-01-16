package controller;

import models.Brett;
import models.GameClock;
import models.Spieler;
import models.Stein;
import views.BrettView;
import views.GameInfoView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

public class GameAndGameInfoMVC extends JFrame {

    private static int dameCount = 1;

    private Brett brett;
    private GameClock clock;

    public GameAndGameInfoMVC(long timeInMin, String schwarz, String weiß, boolean turn, Color farbe, String adr) throws IOException {
        super("Dame" + dameCount++);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1322,995);
        setResizable(false);

        InetAddress group = InetAddress.getByName(adr);
        int port = 1235;

        this.brett = new Brett(timeInMin,schwarz,weiß, turn, farbe, group, port, this);
        BrettView brettView = new BrettView(brett);

        GameInfoView gameInfoView = new GameInfoView(brett.getGameInfo(), this);

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.setDividerLocation(962);
        jSplitPane.setEnabled(false);
        jSplitPane.add(brettView);
        jSplitPane.add(gameInfoView);

        add(jSplitPane);

        setVisible(true);
    }

    public Brett getBrett() {
        return brett;
    }

    public GameClock getClock() {
        return clock;
    }

    public void setClock(GameClock clock) {
        this.clock = clock;
    }

    public static void main(String[] args) throws IOException {
        Spieler spieler = new Spieler("Test", "Test");
        Spieler spieler2 = new Spieler("Test2", "Test2");
        new GameAndGameInfoMVC(30,spieler.getUsername(), spieler2.getUsername(), true, Stein.BLACKSTONE, "225.0.0.1");
    }
}
