package controller.registrierung;

import controller.GameAndMenuMVC;
import controller.anmeldung.AnmeldungMVC;
import controller.menu.MenuMVC;
import models.Anmeldung;
import models.Menu;
import models.Registrierung;
import models.Spieler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrierungController implements ActionListener {

    private Registrierung model;
    private JTextField textField;
    private JPasswordField passwordField;
    private RegistrierungMVC registrierungMVC;

    public RegistrierungController(Registrierung model, JTextField textField, JPasswordField passwordField, RegistrierungMVC registrierungMVC) {
        this.model = model;
        this.textField = textField;
        this.passwordField = passwordField;
        this.registrierungMVC = registrierungMVC;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("zum Anmelden")){
            registrierungMVC.dispose();
            Anmeldung model = new Anmeldung();
            new AnmeldungMVC(model, "Anmeldung");
        }
        else if(s.equals("Abbrechen")){
            System.exit(0);
        }


        else {
            System.out.println("Name: " + textField.getText());
            System.out.println("Passwort: " + passwordField.getText());

            if (Registrierung.speichereRegistrierung(textField.getText(), passwordField.getText())) {
                System.out.println("RegistrierungController: Neuer Spieler und Passwort wurden gespeichert!");
                registrierungMVC.dispose();
                Menu model = new Menu(textField.getText(), new Spieler(textField.getText(), passwordField.getText()));
                new MenuMVC(model,"MENU");
//                Spieler spieler = new Spieler(textField.getText(), passwordField.getText());
//                new GameAndMenuMVC(spieler);
            } else {
                System.out.println("RegistrierungController: Spieler und Passwort leer / schon vorhanden, somit nicht gespeichert");
            }
        }


    }
}