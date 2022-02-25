package grafiken;

import modelle.Anmeldung;

import javax.swing.*;
import java.awt.*;

public class MenuGrafik {
    private static JFrame f;

    public static void menu(String username){
        f = new JFrame();
        f.setLayout(new GridLayout(0, 1));
        f.setPreferredSize(new Dimension(300, 300));
        f.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel (username, SwingConstants.RIGHT);
        f.add(label);
        JButton tutorial = new JButton("Tutorial");
        f.add(tutorial);
        JButton playFive = new JButton("Play 5 Min");
        f.add(playFive);
        JButton playTen = new JButton("Play 10 Min");
        f.add(playTen);
        JButton playThirty = new JButton("Play 30 Min");
        f.add(playThirty);
        JButton logout = new JButton("Logout");
        f.add(logout);

        f.setVisible(true);
        f.pack();
        f.setLocationRelativeTo(null);
    }
}
