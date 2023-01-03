package controller;

import models.Brett;
import models.Spieler;
import socket.ClientDame;
import views.BrettView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

public class BrettMVCSocket extends JFrame {
    public BrettMVCSocket(Brett model, String title){
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(972,992);
        setLayout(new GridLayout(1,2));
        setResizable(false);

        BrettView brettView = new BrettView(model);
        add(brettView);

        JButton button = new JButton("Neues Spiel");
        button.setSize(28,992);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientDame client = new ClientDame(InetAddress.getByName("127.0.0.1"), 1234);
                    client.requestPlayer(model.getSpieler_am_zug().getUsername());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(button);

        setVisible(true);
    }

    public static void main(String[] args) {
        Spieler sp1 = new Spieler("Spieler 1", "Spieler 1");
        Spieler sp2 = new Spieler("Spieler 2", "Spieler 2");

        Brett brett1 = new Brett(30000,sp1, sp2);
        Brett brett2 = new Brett(30000,sp2, sp1);

        new BrettMVCSocket(brett1, "Brett 1");
        new BrettMVCSocket(brett2, "Brett 2");
    }
}
