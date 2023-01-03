package models;

import java.util.ArrayList;

public class Spieler {

    private String username;
    private String passwort;
    private ArrayList bretter;

    public Spieler(String username, String passwort){
        this.username = username;
        this.passwort = passwort;
        bretter = new ArrayList<Brett>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public ArrayList getBretter() {
        return bretter;
    }

    public void addSpiel(Brett brett) {
        bretter.add(brett);
    }
}
