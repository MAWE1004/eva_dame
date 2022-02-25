package grafiken;

import modelle.EventHandler;
import modelle.Spielbrett;

import javax.swing.*;
import java.awt.*;

public class SpielGrafik {

    public static void main (String [ ] args) {
        JFrame f = new JFrame("Spiel");
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.LINE_AXIS));
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        //Spielbrett
        Spielbrett s = new Spielbrett();
        f.add(s.getBrettGrafik());

        // Uhr
        JPanel timeClock = new JPanel();
        timeClock.setLayout (new GridLayout(0, 1));
        JLabel schwarzName = new JLabel ("Name für Schwarz", SwingConstants.CENTER);
        JLabel weissName = new JLabel ("Name für Weiß", SwingConstants.CENTER);
        JLabel label = new JLabel ("", SwingConstants.CENTER);
        timeClock.add(schwarzName);
        timeClock.add (label);
        timeClock.add(weissName);
        Clock clock = new Clock(label);
        JButton b1 = new JButton ("Start");
        timeClock.add (b1);
        JButton b2 = new JButton ("Switch");
        timeClock.add (b2);
        EventHandler h = new EventHandler (clock);
        b1.addActionListener (h);
        b2.addActionListener (h);
        timeClock.setSize(150, 200);

        //Spiel
        f.add(timeClock);
        f.setSize (1920, 1000);
//        f.pack();
        f.setVisible (true);
    } // main
}
