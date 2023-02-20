package controller.menu;

import controller.TutorialMVC;
import controller.anmeldung.AnmeldungMVC;
import models.Anmeldung;
import models.Menu;
import models.Stein;
import socket.ResponseForPlayer;
import socket.SendGegner;
import models.thread.RunGame;
import models.thread.RunMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class MenuController implements ActionListener {

    private Menu model;
    private MenuMVC menuMVC;
    private RunMenu runMenu;
    private ArrayList<RunGame> spiele;

    public MenuController(RunMenu runMenu, MenuMVC menuMVC){
        this.runMenu = runMenu;
        this.model = runMenu.getMenu();
        this.menuMVC = runMenu.getMvc();
        spiele = new ArrayList<RunGame>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Tutorial")){
            System.out.println("Tutorial");
            new TutorialMVC("Tutorial");
        }
        else if (s.equals("Play 5 Min")){
            System.out.println("Play 5 Min");
            try {
                ResponseForPlayer response = model.requestPlayer(5);
                String gegner = response.getGegner();
                if(gegner.equals(" "))
                    gegner = wartenAufGegner(response.getMultiAdr());
                if(response.getFarbe().equals("w"))
                    startSpiel(1,gegner,model.getSpieler().getUsername(), true, Stein.WHITESTONE, response.getMultiAdr());
                else
                    startSpiel(1,model.getSpieler().getUsername(),gegner, false, Stein.BLACKSTONE, response.getMultiAdr());
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
                if(response.getFarbe().equals("w"))
                    startSpiel(10,gegner,model.getSpieler().getUsername(), true, Stein.WHITESTONE, response.getMultiAdr());
                else
                    startSpiel(10,model.getSpieler().getUsername(),gegner, false, Stein.BLACKSTONE, response.getMultiAdr());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (s.equals("Play 30 Min")){
            try {
                ResponseForPlayer response = model.requestPlayer(30);
                String gegner = response.getGegner();
                if(gegner.equals(" "))
                    gegner = wartenAufGegner(response.getMultiAdr());
                if(response.getFarbe().equals("w"))
                    startSpiel(30,gegner,model.getSpieler().getUsername(), true, Stein.WHITESTONE,response.getMultiAdr());
                else
                    startSpiel(30,model.getSpieler().getUsername(),gegner, false, Stein.BLACKSTONE,response.getMultiAdr());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (s.equals("Logout")){
            System.out.println("Logout");
            endAll();
        }
    }

    private void startSpiel(long timeInMin, String schwarz, String weiß, boolean turn, Color farbe, String adr){
        RunGame game = new RunGame(timeInMin, schwarz, weiß, turn, farbe, adr);

        spiele.add(game);
        game.start();
    }

    private String wartenAufGegner(String adr){
        System.out.println("WAITING ... ");
        try {
            InetAddress gruppe = InetAddress.getByName(adr);
            System.out.println("Address " + adr);
            int port = 1235;
            MulticastSocket socket = new MulticastSocket(port);
            socket.setSoTimeout(5000);
            //socket.setInterface(InetAddress.getByName("10.0.3.36"));
            socket.joinGroup(gruppe);
            byte[] buffer = new byte[100];
            DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
            System.out.println("Before Receive");
            SendGegner response = null;
            int retryCount = 5;
            while (retryCount > 0){
                try {
                    socket.receive(receive);
                    buffer = receive.getData();
                    response = new SendGegner().unMarshall(buffer);
                    System.out.println("Received: " + response.getGegner());
                    break;
                } catch (SocketTimeoutException ex) {
                    retryCount--;
                    ex.printStackTrace();
                }
            }

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

    public void endAll(){
        runMenu.closeMenu();
        for (RunGame spiel: spiele) {
            try {
                spiel.getGame().getBrett().sendGameOver((byte) 2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            spiel.getGame().dispose();
        }
        Anmeldung model = new Anmeldung();
        new AnmeldungMVC(model, "Anmeldung");
    }
}