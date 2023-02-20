package viewsNeu;


import modelsNeu.Feld;

import javax.swing.*;

public class FeldView extends JPanel implements FeldListener{

    public FeldView(Feld model){
        super();
        colorChanged(model);
    }

    @Override
    public void colorChanged(Feld model) {
        setBackground(model.getColor());
    }
}