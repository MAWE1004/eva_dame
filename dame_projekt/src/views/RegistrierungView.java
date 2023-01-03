package views;

import models.Registrierung;

import javax.swing.*;

public class RegistrierungView extends JLabel implements RegistrierungListener {


    public RegistrierungView(Registrierung model){
        super();
        modelChanged(model);
    }

    @Override
    public void modelChanged(Registrierung model) {
        setText("Registrierung: ");
    }
}
