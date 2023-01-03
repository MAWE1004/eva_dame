package models;

import views.FeldListener;
import views.GameInfoListener;

import java.util.ArrayList;

public class GameInfo {
    private String user;
    private String gegner;
    private String userStein;
    private String gegnerStein;
    private String blackStone;
    private String whiteStone;
    private long time;
    private ArrayList<GameInfoListener> listeners;

    public GameInfo(){
        this.listeners = new ArrayList<GameInfoListener>();
    }

    public GameInfo(long timeInMin, String user, String gegner, String userStein, String gegnerStein) {
        this.user = user;
        this.gegner = gegner;
        this.userStein = userStein;
        this.gegnerStein = gegnerStein;
        this.time = timeInMin * 60000;
        this.listeners = new ArrayList<GameInfoListener>();
    }

    public String getUser() {
        return user;
    }

    public String getGegner() {
        return gegner;
    }

    public String getUserStein() {
        return userStein;
    }

    public String getGegnerStein() {
        return gegnerStein;
    }

    public long getTime() {
        return time;
    }

    public void setStoneCountBlack(byte count){
        blackStone = Byte.toString(count);
        valueChanged();
    }

    public void setStoneCountWhite(byte count){
        whiteStone = Byte.toString(count);
        valueChanged();
    }

    public void addGameInfoListener(GameInfoListener l) {
        listeners.add(l);
    }

    public void removeGameInfoListener(GameInfoListener l) {
        listeners.remove(l);
    }

    private void valueChanged() {
        for (GameInfoListener l : listeners) {
            System.out.println("Stone Changed");
            l.valueChanged(this);
        }
    }

}
