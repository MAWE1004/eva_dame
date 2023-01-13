package controller;

import models.GameInfo;
import views.GameInfoView;

import javax.swing.*;
import java.awt.*;

public class GameInfoMVC extends JFrame {

    public GameInfoMVC(GameInfo model){
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0,1));
        setSize(new Dimension(250,400));

        GameInfoView view = new GameInfoView(model, null);
        add(view);
        setVisible(true);
    }

    public static void main(String[] args) {
        GameInfo model = new GameInfo(30,"ich", "gegner", "schwarz", "weiß");
        new GameInfoMVC(model);
    }
}
