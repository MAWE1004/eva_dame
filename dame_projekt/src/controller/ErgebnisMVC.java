package controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ErgebnisMVC extends JFrame {

    public ErgebnisMVC(String text, GameAndGameInfoMVC gameAndGameInfoMVC){
        setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(400, 300));

        JLabel setLabelText = new JLabel(text, SwingConstants.CENTER);

        JButton ok = new JButton("Ok");
        // add actionlistener, der spiel disposed
        ErgebnisController ergebnisController = new ErgebnisController(this, gameAndGameInfoMVC);
        ok.addActionListener(ergebnisController);
        add(setLabelText);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        Border padding = BorderFactory.createEmptyBorder(0, 50, 100, 50);
        panel.setBorder(padding);
        add(panel);
        panel.add(ok);

        pack();
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setVisible (true);
        setLocationRelativeTo(null);

    }

    public static void main (String[]args){
        new ErgebnisMVC("Du hast gewonnen!", null);
        new ErgebnisMVC("Du hast verloren!", null);
    }

}
