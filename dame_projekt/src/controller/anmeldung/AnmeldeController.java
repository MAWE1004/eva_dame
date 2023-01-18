package controller.anmeldung;

import controller.registrierung.RegistrierungMVC;
import models.Anmeldung;
import models.Menu;
import models.Registrierung;
import models.Spieler;
import models.thread.RunMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnmeldeController implements ActionListener {

    private Anmeldung model;
    private JTextField textField;
    private JPasswordField passwordField;
    private AnmeldungMVC anmeldungMVC;


    public AnmeldeController(Anmeldung model, JTextField textField, JPasswordField passwordField, AnmeldungMVC anmeldungMVC) {
        this.model = model;
        this.textField = textField;
        this.passwordField = passwordField;
        this.anmeldungMVC = anmeldungMVC;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("zum Registrieren")) {
            anmeldungMVC.dispose();
            Registrierung model = new Registrierung();
            new RegistrierungMVC(model, "Registrierung");
        }
        else if (s.equals("Abbrechen")) {
            System.exit(0);
        }
        else {
            System.out.println("Name: " + textField.getText());
            System.out.println("Passwort: " + passwordField.getText());
            try {

                if (Anmeldung.checkIfUsernameAndPasswordCompatible(textField.getText(), passwordField.getText())) {

                    System.out.println("AnmeldeController: Spieler und Password stimmen überein / vorhanden");
                    anmeldungMVC.dispose();
                    Menu model = new Menu(textField.getText(), new Spieler(textField.getText(), passwordField.getText()));
                    RunMenu runMenu = new RunMenu(model);
                } else {
                    System.out.println("AnmeldeController: Spieler und Password stimmen NICHT überein / NICHT vorhanden");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
