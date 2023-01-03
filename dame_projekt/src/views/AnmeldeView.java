package views;

import models.Anmeldung;

import javax.swing.*;

public class AnmeldeView extends JLabel implements AnmeldeListener {

    public AnmeldeView(Anmeldung model){
        super();
        modelChanged(model);
    }

    @Override
    public void modelChanged(Anmeldung model) {
        setText("Anmeldung: ");
    }
}
