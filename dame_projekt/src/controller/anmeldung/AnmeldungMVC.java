package controller.anmeldung;

import models.Anmeldung;
import views.AnmeldeView;

import javax.swing.*;
import java.awt.*;

public class AnmeldungMVC extends JFrame {

    public AnmeldungMVC(Anmeldung model, String title){
        super(title);
        setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(500, 300));
        AnmeldeView anmeldeView = new AnmeldeView(model);
        model.addListener(anmeldeView);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameTextField = new JTextField();
        JLabel passwordLabel = new JLabel("Passwort: ");
        JPasswordField passwordFieldView = new JPasswordField();

        JButton anmelden = new JButton("Anmelden");
        JButton abbrechen = new JButton("Abbrechen");
        JButton zumRegistrieren = new JButton("zum Registrieren");

        AnmeldeController anmeldeController = new AnmeldeController(model , nameTextField, passwordFieldView, this);

        anmelden.addActionListener(anmeldeController);
        abbrechen.addActionListener(anmeldeController);
        zumRegistrieren.addActionListener(anmeldeController);


        add(anmeldeView);
        add(nameLabel);
        add(nameTextField);
        add(passwordLabel);
        add(passwordFieldView);
        add(anmelden);
        add(abbrechen);
        add(zumRegistrieren);


        pack();
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setVisible (true);
        setLocationRelativeTo(null);
    }
//
//    public static void main(String[] args) {
//        Anmeldung model = new Anmeldung();
//        new AnmeldungMVC(model, "Anmeldung");
//    }


}