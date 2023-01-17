package controller.menu;

import controller.GameAndGameInfoMVC;
import controller.GameAndMenuMVC;
import controller.TutorialMVC;
import controller.anmeldung.AnmeldungMVC;
import models.Anmeldung;
import models.Menu;
import models.Stein;
import socket.ClientDame;
import socket.ResponseForPlayer;
import socket.SendGegner;
import socket.SendZug;
import views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
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
    public MenuController(){
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Tutorial")){
            System.out.println("Tutorial");
//            new GameAndGameInfoMVC();
            new TutorialMVC("Tutorial");
        }
        else if (s.equals("Play 5 Min")){
            System.out.println("Play 5 Min");
            try {
                ResponseForPlayer response = model.requestPlayer(5);
                String gegner = response.getGegner();
                if(gegner.equals(" "))
                    gegner = wartenAufGegner(response.getMultiAdr());
                GameAndGameInfoMVC spiel;
                if(response.getFarbe().equals("w"))
                    spiel = new GameAndGameInfoMVC(1,gegner,model.getSpieler().getUsername(), true, Stein.WHITESTONE, response.getMultiAdr());
                else
                    spiel = new GameAndGameInfoMVC(1,model.getSpieler().getUsername(),gegner, false, Stein.BLACKSTONE, response.getMultiAdr());
                spiele.add(spiel);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        else if (s.equals("Play 10 Min")){
            try {
                ResponseForPlayer response = model.requestPlayer(10);
                String gegner = response.getGegner();
                if(gegner.equals(" "))
                    gegner = wartenAufGegner(response.getMultiAdr());
                GameAndGameInfoMVC spiel;
                if(response.getFarbe().equals("w"))
                    spiel = new GameAndGameInfoMVC(10,gegner,model.getSpieler().getUsername(), true, Stein.WHITESTONE, response.getMultiAdr());
                else
                    spiel = new GameAndGameInfoMVC(10,model.getSpieler().getUsername(),gegner, false, Stein.BLACKSTONE, response.getMultiAdr());
                spiele.add(spiel);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (s.equals("Play 30 Min")){
            try {
                ResponseForPlayer response = model.requestPlayer(5);
                String gegner = response.getGegner();
                if(gegner.equals(" "))
                    gegner = wartenAufGegner(response.getMultiAdr());
                GameAndGameInfoMVC spiel;
                if(response.getFarbe().equals("w"))
                    spiel = new GameAndGameInfoMVC(30,gegner,model.getSpieler().getUsername(), true, Stein.WHITESTONE,response.getMultiAdr());
                else
                    spiel = new GameAndGameInfoMVC(30,model.getSpieler().getUsername(),gegner, false, Stein.BLACKSTONE,response.getMultiAdr());
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
        else if (s.equals("Ok")){
            System.out.println("TESSSSSSSSSSSSSSSSSSSSSSST");
            for (GameAndGameInfoMVC spiel: spiele) {
                if(spiel.getTitle().equals("")){
                }
            }
        }
    }

    private String wartenAufGegner(String adr){
        System.out.println("WAITING ... ");
        try {
            InetAddress gruppe = InetAddress.getByName(adr);
            int port = 1235;
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(gruppe);
            byte[] buffer = new byte[100];
            DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
            socket.receive(receive);
            buffer = receive.getData();
            SendGegner response = new SendGegner().unMarshall(buffer);
            System.out.println("Received: " + response.toString());
            socket.leaveGroup(gruppe);
            socket.close();

            return response.getGegner();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}