package controller;

import models.GameClock;

import javax.swing.*;
import java.awt.*;

public class GameClockMVC extends JFrame {
    public GameClockMVC(long time){
        JFrame f = new JFrame ("Uhr");
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        f.setLayout (new GridLayout(0, 1));
        JLabel label = new JLabel ("", SwingConstants.RIGHT);
        label.setFont(new Font(Font.DIALOG,Font.BOLD, 40));
        f.add (label);
        JButton b1 = new JButton ("Start");
        f.add (b1);
        JButton b2 = new JButton ("Stopp");
        f.add (b2);
        JButton b3 = new JButton ("Null");
        f.add (b3);
        JButton b4 = new JButton ("Ende");
        f.add (b4);
        JButton b5 = new JButton ("Pause");
        f.add(b5);
        GameClock clock = new GameClock (label, time);
        GameClockController h = new GameClockController (clock);
        b1.addActionListener (h);
        b2.addActionListener (h);
        b3.addActionListener (h);
        b4.addActionListener (h);
        b5.addActionListener (h);
        f.setLocation (300, 50);
        f.setSize (150, 200);
        f.setVisible (true);
    }

    public static void main(String[] args) {
        new GameClockMVC(15000);
    }
}
