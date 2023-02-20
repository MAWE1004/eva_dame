package models;

import socket.ClientDame;
import socket.ResponseForPlayer;
import views.MenuListener;

import java.net.InetAddress;
import java.util.ArrayList;

public class Menu {
    private String name;
    private Spieler spieler;
    private ArrayList<MenuListener> listener;

    private String adr;
    private int port;

    public Menu(String name, Spieler spieler, String adr, int port){
        this.name = name;
        this.spieler = spieler;
        listener = new ArrayList<MenuListener>();
        this.adr = adr;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Spieler getSpieler() {
        return spieler;
    }


    private void counterModelChanged(){
        for (MenuListener l : listener) {
            l.nameChanged(this);
        }
    }

    public void addListener(MenuListener l){
        listener.add(l);
    }

    public ResponseForPlayer requestPlayer(int time) throws Exception {
        InetAddress initAdr = InetAddress.getByName(adr);
        ClientDame client = new ClientDame(initAdr, port);
        return client.requestPlayer(spieler.getUsername(), time);
    }

    public boolean serverAlive() throws Exception {
        InetAddress initAdr = InetAddress.getByName(adr);
        ClientDame client = new ClientDame(initAdr, port);
        return client.serverStillRunning();
    }
}