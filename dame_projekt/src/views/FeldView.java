package views;

import controller.FeldController;
import controller.FeldEmptyController;
import controller.FeldQueenController;
import models.Feld;

import javax.swing.*;

public class FeldView extends JPanel implements FeldListener {

    public FeldView(Feld model){
      super();
      add(new SteinView(model.getStone()));
      colorChanged(model);
    }

    @Override
    public void colorChanged(Feld model) {
        setBackground(model.getColor());
    }

    @Override
    public void stoneChanged(Feld model) {
        removeMouseListener(this.getMouseListeners()[0]);
        if(model.getStone() != null){
            if(model.getStone().isQueen()){
                addMouseListener(new FeldQueenController(model));
            }
            else {
                addMouseListener(new FeldController(model));
            }
            add(new SteinView(model.getStone()));
        }
        else{
            addMouseListener(new FeldEmptyController(model));
            removeAll();
        }

        validate();
        repaint();
    }
}
