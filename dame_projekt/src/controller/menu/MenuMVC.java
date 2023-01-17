package controller.menu;

import controller.WindowController;
import models.Menu;
import models.Spieler;
import socket.ServerAliveThread;
import views.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuMVC extends JFrame {

    public MenuMVC(Menu model, String title){

        super(title);
        setLayout(new GridLayout(0,1));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(350, 350));

        JButton tutorial = new JButton("Tutorial");
        JButton playFive = new JButton("Play 5 Min");
        JButton playTen = new JButton("Play 10 Min");
        JButton playThirty = new JButton("Play 30 Min");
        JButton logout = new JButton("Logout");

        MenuController menuController = new MenuController(model, this);

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

        //ServerAliveThread serverAliveThread = new ServerAliveThread(model, menuController);

    }

    public static void main (String[]args){
        Menu model = new Menu("Test", new Spieler("test", "test"));
        new MenuMVC(model, "Menu");
    }

}