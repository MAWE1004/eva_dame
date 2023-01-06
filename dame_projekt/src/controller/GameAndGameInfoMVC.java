package controller;

import models.Brett;
import models.Spieler;
import views.BrettView;
import views.GameInfoView;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;

public class GameAndGameInfoMVC extends JFrame {
    public GameAndGameInfoMVC(long timeInMin, String schwarz, String weiß) throws IOException {
        super("Dame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1322,995);
        setResizable(false);

        InetAddress group = InetAddress.getByName("225.0.0.1");
        int port = 1234;

        Brett brett = new Brett(timeInMin,schwarz,weiß, group, port);
        BrettView brettView = new BrettView(brett);

        GameInfoView gameInfoView = new GameInfoView(brett.getGameInfo());

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.setDividerLocation(962);
        jSplitPane.setEnabled(false);
        jSplitPane.add(brettView);
        jSplitPane.add(gameInfoView);

        add(jSplitPane);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Spieler spieler = new Spieler("Test", "Test");
        Spieler spieler2 = new Spieler("Test2", "Test2");
        new GameAndGameInfoMVC(30,spieler.getUsername(), spieler2.getUsername());
    }
}
