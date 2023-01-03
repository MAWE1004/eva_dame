package controller;

import models.Brett;
import models.Spieler;
import views.BrettView;
import views.GameInfoView;

import javax.swing.*;

public class GameAndGameInfoMVC extends JFrame {
    public GameAndGameInfoMVC(Spieler schwarz, Spieler weiß){
        super("Dame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1322,995);
        setResizable(false);

        Brett brett = new Brett(30000,schwarz,weiß);
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

    public static void main(String[] args) {
        Spieler spieler = new Spieler("Test", "Test");
        Spieler spieler2 = new Spieler("Test2", "Test2");
        new GameAndGameInfoMVC(spieler, spieler2);
    }
}
