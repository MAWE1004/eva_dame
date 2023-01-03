package controller;

import models.Feld;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FeldController implements MouseListener {

    private Feld model;

    public FeldController(Feld model){
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("\u001B[34m" + "Feld angeclickt -> " + model.toString() + " Farbe: " + model.getColor() + "\u001B[0m");

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
