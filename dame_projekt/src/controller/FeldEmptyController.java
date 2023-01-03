package controller;

import models.Feld;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FeldEmptyController implements MouseListener {

    private Feld model;

    public FeldEmptyController(Feld model){
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Feld (leer) angeclickt -> " + model.toString());

        if(model.getColor().equals(Feld.GREENBACKGROUND)){
            model.getBrett().setClickedNewFeld(model);
            model.getBrett().changeStonePosition();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
