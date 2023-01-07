package views;

import javax.swing.*;

public class ErgebnisLabel extends JLabel {


    public void setTextWeissZeitVerloren(){
        setText("Spiel vorbei. Weiß hat verloren! Zeit abgelaufen");
    }
    public void setTextSchwarzZeitVerloren(){
        setText("Spiel vorbei. Schwarz hat verloren! Zeit abgelaufen!");
    }
    public void setTextWeissSteineVerloren(){
        setText("Spiel vorbei. Weiß hat keine Steine mehr. Schwarz gewinnt!");
    }
    public void setTextSchwarzSteineVerloren(){
        setText("Spiel vorbei. Schwarz hat keine Steine mehr. Weiß gewinnt!");
    }
    public void setTextServerAbsturz(){
        setText("Spiel abgebrochen, keine Verbindung zum Server");
    }
}