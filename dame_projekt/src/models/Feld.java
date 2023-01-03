package models;

import views.FeldListener;

import java.awt.*;
import java.util.ArrayList;

public class Feld {

    public final static Color YELLOWBACKGROUND = new Color(255,255,0, 180);
    public final static Color GREENBACKGROUND = new Color(120,255,120, 180);
    public final static Color WHITEBACKGROUND = new Color(238,238,238);

    private Color color;
    private Stein stone;
    private Brett brett;
    private ArrayList<FeldListener> listeners;

    public Feld(){
        this.color = null;
        this.stone = null;
        this.brett = null;
        this.listeners = null;
    }

    public Feld(Color color, Stein stone, Brett brett){
        this.color = color;
        this.stone = stone;
        this.brett = brett;
        this.listeners = new ArrayList<FeldListener>();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stein getStone() {
        return stone;
    }

    public void setStone(Stein stone) {
        this.stone = stone;
    }

    public boolean hasStone(){
        if(stone == null)
            return false;

        return true;
    }

    public Brett getBrett() {
        return brett;
    }

    public void setBrett(Brett brett) {
        this.brett = brett;
    }

    public void addFeldListener(FeldListener l) {
        listeners.add(l);
    }

    public void removeFeldListeners(FeldListener l) {
        listeners.remove(l);
    }

    private void colorModelChanged() {
        for (FeldListener l : listeners) {
            System.out.println("Model Change");
            l.colorChanged(this);
        }
    }

    private void stoneModelChanged() {
        for (FeldListener l : listeners) {
            System.out.println("Stone Changed");
            l.stoneChanged(this);
        }
    }


    public void usingField(){
        //altes Feld zurücksetzten
        if(brett.getClickedFeld() != null){
            brett.getClickedFeld().setBackBackgroundColor();
        }

        // alte mögliche Züge zurücksetzen
        Feld[] possible = brett.getPossibleFelder();

        for(Feld feld: possible){
            if(feld != null){
                feld.setBackBackgroundColor();
            }
        }
        //mögliche Züge

        if(brett.getSpieler_am_zug() == brett.getSpieler_schwarz() && Stein.BLACKSTONE.equals(stone.getFarbe())){
            color = YELLOWBACKGROUND;
            brett.setClickedFeld(this);
            colorModelChanged();

            if(stone.isQueen()){
                System.out.println("QUEEN BLACK");
                brett.possibleFeldWhite(stone.getX(), stone.getY());
                brett.possibleFeldBlack(stone.getX(), stone.getY());

                possible = brett.getPossibleFelder();

                for (Feld feld: possible) {
                    if(feld != null && !feld.hasStone())
                        feld.setBackgroundGreen();
                }

            }
            else {
                brett.possibleFeldBlack(stone.getX(), stone.getY());

                possible = brett.getPossibleFelder();

                if(possible[2] != null && !possible[2].hasStone())
                    possible[2].setBackgroundGreen();
                if(possible[3] != null && !possible[3].hasStone())
                    possible[3].setBackgroundGreen();
            }

        }
        else if (brett.getSpieler_am_zug() == brett.getSpieler_weiß() && Stein.WHITESTONE.equals(stone.getFarbe())){
            color = YELLOWBACKGROUND;
            brett.setClickedFeld(this);
            colorModelChanged();

            if(stone.isQueen()){
                System.out.println("QUEEN WHITE");
                brett.possibleFeldWhite(stone.getX(), stone.getY());
                brett.possibleFeldBlack(stone.getX(), stone.getY());

                possible = brett.getPossibleFelder();

                for (Feld feld: possible) {
                    if(feld != null && !feld.hasStone())
                        feld.setBackgroundGreen();
                }
            }
            else {
                brett.possibleFeldWhite(stone.getX(), stone.getY());

                possible = brett.getPossibleFelder();
                System.out.println("Possible Feld: " + possible[1]);

                if(possible[0] != null && !possible[0].hasStone()) // Weil bei grenze links null überprüfen ob null
                    possible[0].setBackgroundGreen();
                if(possible[1] != null && !possible[1].hasStone())
                    possible[1].setBackgroundGreen();
            }
        }

        if(possible[0] != null){
            System.out.println("Nach links oben: " + possible[0].toString());
        }
        if(possible[1] != null){
            System.out.println("Nach rechts oben: " + possible[1].toString());
        }
        if(possible[2] != null){
            System.out.println("Nach links unten: " + possible[2].toString());
        }
        if(possible[3] != null){
            System.out.println("Nach rechts unten: " + possible[3].toString());
        }

    }

    public void setBackBackgroundColor(){
        color = Color.BLACK;
        colorModelChanged();
    }

    public void setBackgroundGreen(){
        color = GREENBACKGROUND;
        colorModelChanged();
    }

    public void addStone(Stein stone){
        this.stone = stone;
        stoneModelChanged();
    }

    public void deleteStone(){
        stone = null;
        stoneModelChanged();
    }

    public void setToQueen(){
        stone.setQueen(true);
        stoneModelChanged();
    }

}
