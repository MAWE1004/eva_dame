package viewsNeu;

import controller.FeldController;
import controller.FeldEmptyController;
import modelsNeu.Brett;
import modelsNeu.Feld;
import viewsNeu.FeldView;

import javax.swing.*;
import java.awt.*;

public class BrettView extends JPanel {
    private Brett model;
    private Feld[][] felderModelle;

    public BrettView(Brett model){
        super();
        this.model = model;
        this.felderModelle = model.getFelder();

        setLayout(new GridLayout(8, 8));

        setSize(972,992);

        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                FeldView feldView = new FeldView(felderModelle[i][j]);

                add(feldView);
                if(felderModelle[i][j].getColor() == Color.BLACK){
                    felderModelle[i][j].addFeldListener(feldView);

                    if(felderModelle[i][j].getStone() != null){
                        FeldController feldController = new FeldController(felderModelle[i][j]);
                        feldView.addMouseListener(feldController);
                    }
                    else {
                        FeldEmptyController feldEmptyController = new FeldEmptyController (felderModelle[i][j]);
                        feldView.addMouseListener(feldEmptyController);
                    }
                }
            }
        }
    }
}
