package grafiken;

import modelle.Registrierung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class RegistrierenGrafik extends JPanel implements ActionListener {

    private static JFrame f;
    private JTextField anmeldeBereichRegistrieren;
    private JPasswordField passwortBereichRegistrieren;
    private JPanel buttonPaneRegistrierenButtons = new JPanel();
    private JLabel name1 = new JLabel("Name: ");
    private JLabel passwort1 = new JLabel("Passwort: ");
    private JLabel registrieren = new JLabel("Noch nicht registriert?: ");

    private JPanel registrierenPane = new JPanel();

    public RegistrierenGrafik(){
        setPreferredSize(new Dimension(500, 200));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        erstelleRegistrierenGrafik();
        erstelleRegistrierenButtons();
    }

    public void erstelleRegistrierenGrafik(){
        registrierenPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        anmeldeBereichRegistrieren = new JTextField();
        anmeldeBereichRegistrieren.setPreferredSize( new Dimension( 250, 30 ) );
        passwortBereichRegistrieren = new JPasswordField();
        passwortBereichRegistrieren.setPreferredSize( new Dimension( 250, 30 ) );
        registrierenPane.setLayout(new BoxLayout(registrierenPane, BoxLayout.Y_AXIS));

        //   registrieren.setPreferredSize(new Dimension( 300, 30 ));
        name1.setPreferredSize(new Dimension( 300, 30 ));
        passwort1.setPreferredSize(new Dimension( 300, 30 ));
        registrierenPane.add(registrieren);
        registrierenPane.add(Box.createRigidArea(new Dimension(0, 3)));
        registrierenPane.add(name1);
        registrierenPane.add(Box.createRigidArea(new Dimension(0, 3)));
        registrierenPane.add(anmeldeBereichRegistrieren);
        registrierenPane.add(Box.createRigidArea(new Dimension(0, 10)));
        registrierenPane.add(passwort1);
        registrierenPane.add(Box.createRigidArea(new Dimension(0, 3)));
        registrierenPane.add(passwortBereichRegistrieren);
        add(registrierenPane);
    }

    public void erstelleRegistrierenButtons(){
        buttonPaneRegistrierenButtons.setLayout(new BoxLayout(buttonPaneRegistrierenButtons, BoxLayout.LINE_AXIS));
        buttonPaneRegistrierenButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JButton registrierenButton = new JButton("registrieren");
        registrierenButton.addActionListener(this);
        JButton abbrechenRegistrieren = new JButton("abbrechen");
        abbrechenRegistrieren.addActionListener(this);
        JButton zumAnmelden = new JButton("Zum Anmelden");
        zumAnmelden.addActionListener(this);
        buttonPaneRegistrierenButtons.add(Box.createHorizontalGlue());
        buttonPaneRegistrierenButtons.add(abbrechenRegistrieren);
        buttonPaneRegistrierenButtons.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPaneRegistrierenButtons.add(registrierenButton);
        buttonPaneRegistrierenButtons.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPaneRegistrierenButtons.add(zumAnmelden);
        add(buttonPaneRegistrierenButtons);
    }
    public static void register() {

        RegistrierenGrafik r = new RegistrierenGrafik();
        f = new JFrame("Registrierung");
        f.setLayout(new GridLayout(0, 1));
        f.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        f.add(r);
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
        if(s.equals("abbrechen")) {
            System.out.println("abbrechen");
            AnmeldeGrafik.anmelden();
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        }
        else if(s.equals("registrieren")) {
            Registrierung.speichereRegistrierung(anmeldeBereichRegistrieren.getText(), passwortBereichRegistrieren.getText());
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
            System.out.println("Sie müssen sich nochmal anmelden!");
            AnmeldeGrafik.anmelden();
            System.out.println("registrieren");
        }
        else if(s.equals("Zum Anmelden")){
            System.out.println("Zum Anmelden");
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
            AnmeldeGrafik.anmelden();
        }
    }
}
