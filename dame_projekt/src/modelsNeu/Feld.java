package modelsNeu;

import viewsNeu.FeldListener;

import java.awt.*;
import java.util.ArrayList;

public class Feld {

    private Color color;
    private boolean hasStone;
    //coordinates
    private int x;
    private int y;
    private ArrayList<FeldListener> listeners;

    public Feld(Color color,boolean hasStone, int x, int y){
        this.color = color;
        this.hasStone = hasStone;
        this.x = x;
        this.y = y;
        this.listeners = new ArrayList<FeldListener>();
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isHasStone() {
        return hasStone;
    }

    public void setHasStone(boolean hasStone) {
        this.hasStone = hasStone;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void addFeldListener(FeldListener l) {
        listeners.add(l);
    }

    private void colorModelChanged() {
        for (FeldListener l : listeners) {
            System.out.println("Model Change");
            l.colorChanged(this);
        }
    }
}
