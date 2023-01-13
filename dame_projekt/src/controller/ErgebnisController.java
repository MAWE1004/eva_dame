package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErgebnisController implements ActionListener {

    private ErgebnisMVC ergebnisMVC;

    public ErgebnisController(ErgebnisMVC ergebnisMVC){
        this.ergebnisMVC = ergebnisMVC;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ergebnisMVC.dispose();
    }
}
