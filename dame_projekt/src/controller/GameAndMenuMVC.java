package controller;

import controller.menu.MenuController;
import controller.menu.MenuMVC;
import models.Brett;
import models.GameInfo;
import models.Menu;
import models.Spieler;
import views.BrettView;
import views.GameInfoView;
import views.MenuView;

import javax.swing.*;
import java.awt.*;

public class GameAndMenuMVC extends JFrame {
    public GameAndMenuMVC(Spieler spieler){
        super("Dame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1322,995);
        setResizable(false);

        Brett brett = new Brett(30000,spieler.getUsername(),spieler.getUsername());
        BrettView brettView = new BrettView(brett);

        GameInfoView gameInfoView = new GameInfoView(brett.getGameInfo(), null);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(0,1));
        leftPanel.add(gameInfoView);

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.setDividerLocation(962);
        jSplitPane.setEnabled(false);
        jSplitPane.add(brettView);
        jSplitPane.add(leftPanel);

        add(jSplitPane);
        setVisible(true);

        brett.waitingForTurn();
    }

    public static void main(String[] args) {
        Spieler spieler = new Spieler("Test", "Test");
        new GameAndMenuMVC(spieler);
    }
}
