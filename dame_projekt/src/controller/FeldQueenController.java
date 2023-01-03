package controller;

import models.Feld;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FeldQueenController implements MouseListener {
    private Feld model;

    public FeldQueenController(Feld model){
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Feld (Queen) angeklickt -> " + model.toString());

        model.getBrett().setClickedNewFeld(model);
        model.usingField();
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
