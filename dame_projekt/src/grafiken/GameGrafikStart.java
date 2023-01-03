package grafiken;

import modelle.Spiel;
import modelle.Spielbrett;

import javax.swing.*;

public class GameGrafikStart extends JPanel {
    private Spielbrett s;

    public GameGrafikStart(Spielbrett s){
        this.s = s;
        paint();
    }

    private void paint() {
        JFrame game = new JFrame("Dame");
        //JPanel m = new JPanel();
        //m.setBounds(0, 0, 1000, 1000);

        //BrettGrafik brett = new BrettGrafik(s);
        //m.add(brett.getGrafik());

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(972,992);
        game.add(s.getBrettGrafik());
        //game.add(m);

        game.setVisible(true);
    }
}
