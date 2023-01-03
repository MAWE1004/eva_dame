package controller.menu;

import controller.GameAndGameInfoMVC;
import controller.GameAndMenuMVC;
import controller.anmeldung.AnmeldungMVC;
import models.Anmeldung;
import models.Menu;
import views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuController implements ActionListener {

    private Menu model;
    private MenuMVC menuMVC;
    private ArrayList<GameAndGameInfoMVC> spiele;

    public MenuController(Menu model, MenuMVC menuMVC){
        this.model = model;
        this.menuMVC = menuMVC;
        spiele = new ArrayList<GameAndGameInfoMVC>();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Tutorial")){
            System.out.println("Tutorial");
//            new GameAndGameInfoMVC();
        }
        else if (s.equals("Play 5 Min")){
            System.out.println("Play 5 Min");
            GameAndGameInfoMVC spiel = new GameAndGameInfoMVC(model.getSpieler(),model.getSpieler());
            spiele.add(spiel);
        }
        else if (s.equals("Play 10 Min")){
            System.out.println("Play 10 Min");
            GameAndGameInfoMVC spiel = new GameAndGameInfoMVC(model.getSpieler(),model.getSpieler());
            spiele.add(spiel);
        }
        else if (s.equals("Play 30 Min")){
            System.out.println("Play 30 Min");
            GameAndGameInfoMVC spiel = new GameAndGameInfoMVC(model.getSpieler(),model.getSpieler());
            spiele.add(spiel);
        }
        else if (s.equals("Logout")){
            System.out.println("Logout");
            for (GameAndGameInfoMVC spiel: spiele) {
                spiel.dispose();
            }
            menuMVC.dispose();
            Anmeldung model = new Anmeldung();
            new AnmeldungMVC(model, "Anmeldung");
        }
    }
}