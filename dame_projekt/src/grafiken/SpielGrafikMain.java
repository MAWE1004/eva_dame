package grafiken;

import modelle.EventHandler;
import modelle.Spielbrett;

import javax.swing.*;
import java.awt.*;

public class SpielGrafikMain {
    public static void main(String[]args){

        //Erstellung eines JFrames
        JFrame meinJFrame = new JFrame();
        meinJFrame.setTitle("Spiel");
        meinJFrame.setSize(450,300);
        meinJFrame.setMinimumSize(new Dimension(1472, 992));

        /*Erzeugung der Jpanel-Objekte*/
        //links
        BrettGrafik spiel = new BrettGrafik();
        //972, 992
        //JPanel spiel = new JPanel();
        //rechts
        JPanel split = new JPanel();
        JPanel timeClock = new JPanel();
        JPanel chat = new JPanel();

        /*Zuweisung der JPanels*/
        //Spiel
        //Split
        //split.setLayout(new GridLayout(0, 1));
        split.setLayout(new BoxLayout(split, BoxLayout.Y_AXIS));
        split.add(timeClock);
        split.add(Box.createRigidArea(new Dimension(0,10)));
        split.add(ChatGrafik.chat());

        //Clock
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
    //    timeClock.setSize(150, 200);
        //Chat
        chat.setBackground(Color.GREEN);

        //Erzeugung eines JSplitPane-Objekts
        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitpane.setLeftComponent(spiel);
        splitpane.setRightComponent(split);
        splitpane.setEnabled(false);

        //Anzeige und Rest
        meinJFrame.setPreferredSize(new Dimension(1920, 1080));
        meinJFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        meinJFrame.add(splitpane);

        meinJFrame.setLocationRelativeTo(null);
        meinJFrame.setVisible(true);

    }
}
