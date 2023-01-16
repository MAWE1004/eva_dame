package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErgebnisController implements ActionListener {

    private ErgebnisMVC ergebnisMVC;
    private GameAndGameInfoMVC gameAndGameInfoMVC;

    public ErgebnisController(ErgebnisMVC ergebnisMVC, GameAndGameInfoMVC gameAndGameInfoMVC){
        this.ergebnisMVC = ergebnisMVC;
        this.gameAndGameInfoMVC = gameAndGameInfoMVC;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ergebnisMVC.dispose();
        gameAndGameInfoMVC.dispose();
    }
}
