package controller.menu;

import controller.GameAndGameInfoMVC;
import controller.GameAndMenuMVC;
import controller.anmeldung.AnmeldungMVC;
import models.Anmeldung;
import models.Menu;
import socket.ClientDame;
import socket.ResponseForPlayer;
import views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
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
        else if (s.equals("Play 1 Min")){
            System.out.println("Play 1 Min");
            try {
                ResponseForPlayer response = model.requestPlayer(1);
                GameAndGameInfoMVC spiel;
                if(response.getFarbe().equals("w"))
                    spiel = new GameAndGameInfoMVC(1,response.getGegner(),model.getSpieler().getUsername());
                else
                    spiel = new GameAndGameInfoMVC(1,model.getSpieler().getUsername(),response.getGegner());
                spiele.add(spiel);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        else if (s.equals("Play 3 Min")){
            try {
                ResponseForPlayer response = model.requestPlayer(3);
                GameAndGameInfoMVC spiel;
                if(response.getFarbe().equals("w"))
                    spiel = new GameAndGameInfoMVC(3,response.getGegner(),model.getSpieler().getUsername());
                else
                    spiel = new GameAndGameInfoMVC(3,model.getSpieler().getUsername(),response.getGegner());
                spiele.add(spiel);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (s.equals("Play 5 Min")){
            try {
                ResponseForPlayer response = model.requestPlayer(5);
                GameAndGameInfoMVC spiel;
                if(response.getFarbe().equals("w"))
                    spiel = new GameAndGameInfoMVC(5,response.getGegner(),model.getSpieler().getUsername());
                else
                    spiel = new GameAndGameInfoMVC(5,model.getSpieler().getUsername(),response.getGegner());
                spiele.add(spiel);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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