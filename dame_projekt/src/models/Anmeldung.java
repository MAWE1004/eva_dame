package models;

import socket.ClientDame;
import views.AnmeldeListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Anmeldung {
    private ArrayList<AnmeldeListener> listener;
    private static String adr = "127.0.0.1";
    private static int port = 1234;

    public Anmeldung(String adr, int port) {
        listener = new ArrayList<AnmeldeListener>();
        this.adr = adr;
        this.port = port;
    }

    public String getAdr() {
        return adr;
    }

    public int getPort() {
        return port;
    }

    public static boolean checkIfUsernameAndPasswordCompatible(String name, String password) throws Exception {
        InetAddress initAdr = InetAddress.getByName(adr);
        ClientDame client = new ClientDame(initAdr, port);
        return client.requestAnmeldung(name, password);
    }


    private void timeModelChanged(){
        for (AnmeldeListener l : listener) {
            l.modelChanged(this);
        }
    }

    public void addListener(AnmeldeListener l){
        listener.add(l);
    }
}