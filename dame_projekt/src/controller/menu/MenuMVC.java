package controller.menu;

import models.Menu;
import models.Spieler;
import models.thread.RunMenu;

import javax.swing.*;
import java.awt.*;

public class MenuMVC extends JFrame {
    private MenuController menuController;

    public MenuMVC(RunMenu runMenu, String title){

        super(title);
        setLayout(new GridLayout(0,1));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(350, 350));

        JButton tutorial = new JButton("Tutorial");
        JButton playFive = new JButton("Play 5 Min");
        JButton playTen = new JButton("Play 10 Min");
        JButton playThirty = new JButton("Play 30 Min");
        JButton logout = new JButton("Logout");

        menuController = new MenuController(runMenu, this);

        tutorial.addActionListener(menuController);
        playFive.addActionListener(menuController);
        playTen.addActionListener(menuController);
        playThirty.addActionListener(menuController);
        logout.addActionListener(menuController);

        add(tutorial);
        add(playFive);
        add(playTen);
        add(playThirty);
        add(logout);
        setVisible(true);

    }

    public MenuController getMenuController() {
        return menuController;
    }

    public static void main (String[]args){
//        Menu model = new Menu("Test", new Spieler("test", "test"));
//        new MenuMVC(null, "Menu");
    }

}