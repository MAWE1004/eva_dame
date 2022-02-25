package grafiken;

import modelle.Anmeldung;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

public class AnmeldeGrafik extends JPanel implements ActionListener {

    private static JFrame f;


    private String username;
    private JTextField anmeldeBereich;
    private JPasswordField passwortBereich;
    private JPanel textFieldPane = new JPanel();
    private JPanel buttonPane = new JPanel();
    private JLabel name = new JLabel("Name: ");
    private JLabel passwort = new JLabel("Passwort: ");
//    RegistrierenGrafik reg;

    public AnmeldeGrafik(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 200));
        erstelleTextFields();
        erstelleAnmeldeGrafik();
//        reg = new RegistrierenGrafik();
//        reg.setVisible(false);
//        add(reg);
    }

    public String getUsername() {
        return username;
    }

    public void erstelleTextFields(){
        textFieldPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        anmeldeBereich = new JTextField();
        anmeldeBereich.setPreferredSize( new Dimension( 250, 30 ) );
        passwortBereich = new JPasswordField();
        passwortBereich.setPreferredSize( new Dimension( 250, 30 ) );
        textFieldPane.setLayout(new BoxLayout(textFieldPane, BoxLayout.Y_AXIS));

        name.setPreferredSize(new Dimension( 300, 30 ));
        passwort.setPreferredSize(new Dimension( 300, 30 ));
        textFieldPane.add(name);
        textFieldPane.add(Box.createRigidArea(new Dimension(0, 3)));
        textFieldPane.add(anmeldeBereich);
        textFieldPane.add(Box.createRigidArea(new Dimension(0, 10)));
        textFieldPane.add(passwort);
        textFieldPane.add(Box.createRigidArea(new Dimension(0, 3)));
        textFieldPane.add(passwortBereich);
        textFieldPane.setBackground(Color.LIGHT_GRAY);

        add(textFieldPane);
    }

    public void erstelleAnmeldeGrafik(){

        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JButton anmelden = new JButton("anmelden");
        anmelden.addActionListener(this);
        JButton abbrechen = new JButton("beenden");
        abbrechen.addActionListener(this);
        JButton registrieren = new JButton("Zum registrieren");
        registrieren.addActionListener(this);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(abbrechen);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(anmelden);
        buttonPane.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPane.add(registrieren);
        buttonPane.setBackground(Color.LIGHT_GRAY);
        add(buttonPane);
    }

    public static void anmelden(){
        AnmeldeGrafik a = new AnmeldeGrafik();
        f = new JFrame("Anmeldung");
        f.setLayout(new GridLayout(0, 1));
        f.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        f.add(a);
//        f.setSize (555, 200);

        f.setLocationRelativeTo(null);
        f.repaint();
        f.revalidate();
        f.validate();
        f.setResizable(true);
        f.pack();
        f.setVisible (true);
    }

    public void actionPerformed (ActionEvent e) {
        String s = e.getActionCommand ();
        if (s.equals ("anmelden")) {
            System.out.println("anmelden");
            try {
                username = anmeldeBereich.getText();
                if (Anmeldung.checkIfUsernameAndPasswordCompatible(username, passwortBereich.getText())){
                    MenuGrafik.menu(username);
                    f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                }else{
                    System.out.println("Username oder Passwort stimmen nicht überein!");
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if (s.equals ("beenden") ) {
            System.exit(0);
        }
        else if (s.equals ("Zum registrieren") ) {
            System.out.println("Zum registrieren");
            RegistrierenGrafik.register();
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
//            reg.setVisible(true);
//            reg.setPreferredSize(new Dimension(1000, 400));
        }
    }


}
