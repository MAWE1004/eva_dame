package controller.menu;

import models.Menu;
import models.Spieler;
import views.MenuView;

import javax.swing.*;
import java.awt.*;

public class MenuMVC extends JFrame {


    public MenuMVC(Menu model, String title){

        super(title);
        setLayout(new GridLayout(0,1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(350, 350));
//        MenuView menuView = new MenuView(model);
//        model.addListener(menuView);

        JButton tutorial = new JButton("Tutorial");
        JButton playFive = new JButton("Play 5 Min");
        JButton playTen = new JButton("Play 10 Min");
        JButton playThirty = new JButton("Play 30 Min");
        JButton logout = new JButton("Logout");

        MenuController menuController = new MenuController(model, this);
//
        tutorial.addActionListener(menuController);
        playFive.addActionListener(menuController);
        playTen.addActionListener(menuController);
        playThirty.addActionListener(menuController);
        logout.addActionListener(menuController);

//        add(menuView);
        add(tutorial);
        add(playFive);
        add(playTen);
        add(playThirty);
        add(logout);
        setVisible(true);
//        pack();
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

    }

    public static void main (String[]args){
        Menu model = new Menu("Test", new Spieler("test", "test"));
        new MenuMVC(model, "Menu");
    }

}