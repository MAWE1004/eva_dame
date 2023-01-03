package views_old;

import javax.swing.*;

public class GameGrafik extends JPanel {

    public static void main(String[] args) {
        JFrame game = new JFrame("Dame");
        BrettGrafik b = new BrettGrafik();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(972,992);
        game.add(b);

        game.setVisible(true);
    }
}
