package controller.registrierung;

import models.Registrierung;
import views.RegistrierungView;

import javax.swing.*;
import java.awt.*;

public class RegistrierungMVC extends JFrame {

    public RegistrierungMVC(Registrierung model, String title){
        super(title);
        setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(500, 300));
        RegistrierungView registrierungView = new RegistrierungView(model);
        model.addListener(registrierungView);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameTextField = new JTextField();
        JLabel passwordLabel = new JLabel("Passwort: ");
        JPasswordField passwordFieldView = new JPasswordField();

        JButton registrieren = new JButton("Registrieren");
        JButton abbrechen = new JButton("Abbrechen");
        JButton zumAnmelden = new JButton("zum Anmelden");

        RegistrierungController registrierungController = new RegistrierungController(model , nameTextField, passwordFieldView, this);
        registrieren.addActionListener(registrierungController);
        abbrechen.addActionListener(registrierungController);
        zumAnmelden.addActionListener(registrierungController);

        add(registrierungView);
        add(nameLabel);
        add(nameTextField);
        add(passwordLabel);
        add(passwordFieldView);
        add(registrieren);
        add(abbrechen);
        add(zumAnmelden);

        pack();
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setVisible (true);
        setLocationRelativeTo(null);

    }

    public static void main(String[]args) {
        Registrierung model = new Registrierung();
        new RegistrierungMVC(model, "Registrierung");
    }

}