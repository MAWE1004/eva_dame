package views;

import models.Stein;

import javax.swing.*;
import java.awt.*;

public class SteinView extends JPanel {
    final static ImageIcon image_schwarz = new ImageIcon("C:\\Users\\Marcel\\Desktop\\Schule2\\5. Semester\\EVA\\EVAProjekt\\eva_dame\\dame_projekt\\src\\icons\\krone_schwarz.png");
    final static ImageIcon image_weiß = new ImageIcon("C:\\Users\\Marcel\\Desktop\\Schule2\\5. Semester\\EVA\\EVAProjekt\\eva_dame\\dame_projekt\\src\\icons\\krone_weiß.png");

    private Stein model;


    public SteinView(Stein model) {
        this.model = model;
        setBackground(null);
        if (model != null) {
            setName(model.getName());
        }
    }

    public void paintComponent(Graphics g) {
        if(model != null) {
            super.paintComponent(g);


            setSize(119, 119);
            setLocation(0, 0);
            g.setColor(model.getFarbe());
            int diameter = Math.min(getWidth(), getHeight()) - 6;
            g.fillOval(3, 3, diameter, diameter);

            if(model.isQueen()){
                JLabel label = null;
                if(model.getFarbe().equals(Stein.WHITESTONE))
                    label = new JLabel(image_schwarz);
                else if(model.getFarbe().equals(Stein.BLACKSTONE))
                    label = new JLabel(image_weiß);

                    add(label);
                    validate();
                    repaint();
            }
        }
    }
}
