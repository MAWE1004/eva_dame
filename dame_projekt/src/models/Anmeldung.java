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

    public Anmeldung() {
        listener = new ArrayList<AnmeldeListener>();
    }


    public static boolean checkIfUsernameAndPasswordCompatible(String name, String password) throws Exception {
//        InetAddress adr = InetAddress.getByName("10.0.3.36");
        InetAddress adr = InetAddress.getByName("127.0.0.1");
        int port = 1234;
        ClientDame client = new ClientDame(adr, port);
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