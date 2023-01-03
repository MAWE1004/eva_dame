package controller;

import models.Brett;
import models.Spieler;
import socket.ClientDame;
import views.BrettView;
import views.GameInfoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.InetAddress;

public class BrettMVC extends JFrame{
    public BrettMVC(Brett model, ClientDame client, String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1458,1025);
        setResizable(false);


        BrettView brettView = new BrettView(model);
        add(brettView);

        //SidePanel
        JPanel side = new JPanel();
        side.setLayout(new GridLayout(2,1));
//        side.setSize(486, 992);
        JPanel menu = new JPanel();
        menu.setSize(486, 100);
        menu.setBackground(Color.LIGHT_GRAY);

        Spieler sp1 = new Spieler("Spieler 1", "Spieler 1");
        Spieler sp2 = new Spieler("Spieler 2", "Spieler 2");
        menu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    client.requestPlayer(model.getName());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                new BrettMVC(new Brett(30000,sp1,sp2), client,"Brett");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

//        GameInfoView clock = new GameInfoView();
//        clock.setSize(486, 100);
//        clock.setBackground(Color.GREEN);

        side.add(menu);
//        side.add(clock);

        add(side);

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        Spieler sp1 = new Spieler("Spieler 1", "Spieler 1");
        Spieler sp2 = new Spieler("Spieler 2", "Spieler 2");
        Brett brett1 = new Brett(30000,sp1, sp2);
        Brett brett2 = new Brett(30000,sp1,sp2);

        InetAddress adr = InetAddress.getByName("127.0.0.1");
        int port = 1234;
        ClientDame client = new ClientDame(adr, port);

        new BrettMVC(brett1, client, "Brett1");
        //Für ein identisches 2. Fenster, änderungen oben führen zu den gleichen im unteren
//        new BrettMVC(brettModel, "Brett Spieler 2");
        new BrettMVC(brett2, client, "Brett2");
    }
}
