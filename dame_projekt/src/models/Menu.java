package models;

import views.MenuListener;

import java.util.ArrayList;

public class Menu {
    private String name;
    private Spieler spieler;
    private ArrayList<MenuListener> listener;

    public Menu(String name, Spieler spieler){
        this.name = name;
        this.spieler = spieler;
        listener = new ArrayList<MenuListener>();
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

    //    public void add() {
//        if(counter < 10) {
//            counter += 1;
//            counterModelChanged();
//        }
//    }
//    public void sub() {
//        if(counter > 0) {
//            counter-=1;
//            counterModelChanged();
//        }
//    }

    private void counterModelChanged(){
        for (MenuListener l : listener) {
            l.nameChanged(this);
        }
    }

    public void addListener(MenuListener l){
        listener.add(l);
    }
}