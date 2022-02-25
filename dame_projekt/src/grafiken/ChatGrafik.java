package grafiken;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ChatGrafik {

    public static JPanel chat(){
        //Erzeugung JTextField und JButton
        JTextField nachrichtText = new JTextField();
        nachrichtText.setPreferredSize( new Dimension( 250, 30 ) );
        JButton senden = new JButton("Senden");

        // Erzeugung der JPanels
        JPanel chat = new JPanel();
        chat.setBackground(Color.GREEN);
        JPanel anzeige = new JPanel();
        anzeige.setBackground(Color.LIGHT_GRAY);
        JPanel nachricht = new JPanel();
        //nachricht.setBackground(Color.BLUE);

        //JScrollPane für Anzeige wird erzeugt
        JScrollPane scrollPane = new JScrollPane (anzeige,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Nachricht wird erstellt
        nachricht.add(nachrichtText);
        nachricht.add(senden);
        //nachricht.setPreferredSize(new Dimension(250, -550));
        // Chat wird erzeugt
        //chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
        chat.setLayout(new BorderLayout());
        chat.add(scrollPane, BorderLayout.CENTER);
        chat.add(nachricht, BorderLayout.PAGE_END);
        //chat.setPreferredSize(new Dimension(450, 300));
        return chat;
    }

    public static void main(String[]args){
        JFrame meinJFrame = new JFrame();
        meinJFrame.setTitle("Spiel");
       // meinJFrame.setSize(450,300);
        meinJFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        meinJFrame.add(ChatGrafik.chat());
        meinJFrame.pack();
        meinJFrame.setLocationRelativeTo(null);
        meinJFrame.setVisible(true);
    }
}
