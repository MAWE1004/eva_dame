package models;

import socket.ClientDame;
import views.RegistrierungListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Registrierung {

    private static ArrayList<Spieler> spielerListe;
    private ArrayList<RegistrierungListener> listener;

    public Registrierung(){
        listener = new ArrayList<RegistrierungListener>();
    }
    private static boolean checkIfUsernameVorhanden(String username) throws FileNotFoundException {
        String line;
        Scanner read = new Scanner(new File("anmeldung.txt"));
        while (read.hasNextLine()){
            line = read.nextLine();
            if(line.charAt(0) == 'u') {
                line = line.substring(3);
                System.out.println(line);
                if (line.toLowerCase().equals(username.toLowerCase())) {
                    System.out.println("Username schon vorhanden!");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean speichereRegistrierung(String name, String password) {
        if(name.isEmpty() && password.isEmpty()){
            System.out.println("Username und Passwort dÃ¼rfen nicht leer sein!");
            return false;
        }

        try {
            InetAddress adr = InetAddress.getByName("10.0.3.36");
            int port = 1234;
            ClientDame client = new ClientDame(adr, port);
            return client.requestRegistrierung(name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    private void timeModelChanged(){
        for (RegistrierungListener l : listener) {
            l.modelChanged(this);
        }
    }

    public void addListener(RegistrierungListener l){
        listener.add(l);
    }


}