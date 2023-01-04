package models;

import socket.ClientDame;

import java.awt.*;
import java.net.InetAddress;
import java.util.Arrays;

public class Brett extends Thread{
    private static int countGame = 1;

    private Feld[][] felder;
    private Feld clickedFeld;
    private Feld clickedNewFeld;
    private Feld schlagendesFeld;
    private Feld[] possibleFelder;
    private Feld[] schlagendeFelder;

    private Spieler spieler_schwarz;
    private Spieler spieler_weiß;
    private Spieler spieler_am_zug;

    private GameInfo gameInfo;

    private byte countblack;
    private byte countwhite;


    public Brett(long timeInMin,Spieler schwarz, Spieler weiß){
        super("Brett " + countGame++);

        spieler_schwarz = schwarz;
        spieler_weiß = weiß;
        spieler_am_zug = schwarz;
        gameInfo = new GameInfo(timeInMin,spieler_schwarz.getUsername(),spieler_weiß.getUsername(),"schwarz", "weiß");
        felder = new Feld[10][10];
        clickedFeld = null;
        clickedNewFeld = null;
        schlagendesFeld = null;
        possibleFelder = new Feld[]{null,null,null,null}; // 0 und 1 für weiß, 2 und 3 für schwarz, 0-3 für dame
        schlagendeFelder= new Feld[]{null,null,null,null};
        countblack = 12;
        countwhite = 12;

        initializeBrett();

//        start();
    }

    public synchronized void run(){
        initializeBrett();
        System.out.println("SPIEL " + getName() + " gestartet");
    }

    private void initializeBrettEmpty(){
        for(int i = 1, feld = 2, name = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){

                Feld f = new Feld(Feld.WHITEBACKGROUND, null, this);
                if((feld == j)){
                    f.setColor(Color.BLACK);
                    if(feld % 8 == 0 | feld == 7){
                        if(i % 2 == 1)
                            feld = -1;
                        else
                            feld = 0;
                    }

                    feld+=2;
                }
                //Add Feld to Brett
                felder[i][j] = f;
            }
        }
        easyLayout();
    }

    private void initializeBrett(){
        for(int i = 1, feld = 2, name = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){

                Feld f = new Feld(Feld.WHITEBACKGROUND, null, this);
                if((feld == j)){
                    f.setColor(Color.BLACK);
                    if(i <= 3){ // BLACK STONES
                        f.setStone(new Stein(Stein.BLACKSTONE, "black_" + name++, i, j));

                    }
                    else if(i >= 6){ // WHITE STONES
                        f.setStone(new Stein(Stein.WHITESTONE,"white_" + (name++ - 12), i, j));
                    }
                    if(feld % 8 == 0 | feld == 7){
                        if(i % 2 == 1)
                            feld = -1;
                        else
                            feld = 0;
                    }

                    feld+=2;
                }
                //Add Feld to Brett
                felder[i][j] = f;
            }
        }
        easyLayout();
    }

    public Feld[][] getFelder() {
        return felder;
    }

    public void setFelder(Feld[][] felder) {
        this.felder = felder;
    }

    public Feld getFeldAt(int x, int y){
        return felder[x][y];
    }

    public Feld getClickedFeld() {
        return clickedFeld;
    }

    public void setClickedFeld(Feld clickedFeld) {
        this.clickedFeld = clickedFeld;
    }

    public Feld getClickedNewFeld() {
        return clickedNewFeld;
    }

    public void setClickedNewFeld(Feld clickedNewFeld) {
        this.clickedNewFeld = clickedNewFeld;
    }

    public Feld[] getPossibleFelder() {
        return possibleFelder;
    }

    public void setPossibleFelder(Feld[] possibleFelder) {
        this.possibleFelder = possibleFelder;
    }

    public Spieler getSpieler_am_zug() {
        return spieler_am_zug;
    }

    public Spieler getSpieler_schwarz() {
        return spieler_schwarz;
    }

    public Spieler getSpieler_weiß() {
        return spieler_weiß;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public byte getCountblack() {
        return countblack;
    }

    public byte getCountwhite() {
        return countwhite;
    }

    public void decreaseCountBlack() {
        countblack--;
        gameInfo.setStoneCountBlack(countblack);
    }

    public void decreaseCountWhite() {
        countwhite--;
        gameInfo.setStoneCountWhite(countwhite);
    }

    public void possibleFeldBlack(int x, int y){
        System.out.println("X^ : " + x + " y-> : " + y);
        try{
            switch(y){
                case 1: //Grenze Links
                    possibleFelder[3] = possibleFeldBlackRight(x, y);
                    break;
                case 8: //Grenze rechts
                    possibleFelder[2] = possibleFeldBlackLeft(x, y);
                    break;
                default: //Keine Grenze
                     possibleFelder[2] = possibleFeldBlackLeft(x, y);
                     possibleFelder[3] = possibleFeldBlackRight(x, y);
            }
            System.out.println("Possible Felder: " + possibleFelder.toString());
            System.out.println("Zu SchlagendeFelder: " + schlagendeFelder[2]);

        } catch(Exception e){
            e.printStackTrace();
            //Setze bei Fehler alle Felder wieder auf null bzw Black MIT EIGENER METHODE
        }

    }

    private Feld possibleFeldBlackRight(int x, int y){
        Feld fp = felder[x+1][y+1];
        try {
            if(fp.getStone() != null){
                if (felder[x][y].getStone().getFarbe().equals(Stein.BLACKSTONE) && fp.getStone().getFarbe().equals(Stein.WHITESTONE) & felder[x + 2][y + 2].getStone() == null) {
                    schlagendeFelder[3] = fp;
                    return felder[x + 2][y + 2];
                }
            }
        } catch (NullPointerException e){

        }

        return fp;
    }

    private Feld possibleFeldBlackLeft(int x, int y) {
        Feld fp = felder[x+1][y-1];
        try {
            if(fp.getStone() != null){
                if (felder[x][y].getStone().getFarbe().equals(Stein.BLACKSTONE) && fp.getStone().getFarbe().equals(Stein.WHITESTONE) & felder[x + 2][y - 2].getStone() == null) {
                    schlagendeFelder[2] = fp;
                    return felder[x + 2][y - 2];
                }
            }
        } catch (NullPointerException e){

        }

        return fp;
    }

    public void possibleFeldWhite(int x, int y) {
        System.out.println("X^ : " + x + " y-> : " + y);

        try {
            switch (y) {
                case 1: //Grenze Links
                    possibleFelder[1] = possibleFeldWhiteRight(x, y); //Zug rechts
                    break;
                case 8: //Grenze rechts
                    possibleFelder[0] = possibleFeldWhiteLeft(x, y);
                    break;
                default: //Keine Grenze
                    possibleFelder[0] = possibleFeldWhiteLeft(x, y); //Zug links
                    possibleFelder[1] = possibleFeldWhiteRight(x, y); //Zug rechts

            }

        } catch (NullPointerException e){

        } catch(Exception e){
            e.printStackTrace();
            //Setze bei Fehler alle Felder wieder auf null bzw Black MIT EIGENER METHODE
        }

    }

    private Feld possibleFeldWhiteLeft(int x, int y) {
        Feld fp = felder[x - 1][y - 1];
        try {
            if(fp.getStone() != null){
                if (felder[x][y].getStone().getFarbe().equals(Stein.WHITESTONE) && fp.getStone().getFarbe().equals(Stein.BLACKSTONE) & felder[x - 2][y - 2].getStone() == null) {
                    schlagendeFelder[0] = fp;
                    return felder[x - 2][y - 2];
                }
            }
        } catch (NullPointerException e){

        }

        return fp;
    }

    private Feld possibleFeldWhiteRight(int x, int y) {
        Feld fp = felder[x - 1][y + 1];
        try {
            if(fp.getStone() != null){
                if (felder[x][y].getStone().getFarbe().equals(Stein.WHITESTONE) && fp.getStone().getFarbe().equals(Stein.BLACKSTONE) & felder[x - 2][y + 2].getStone() == null) {
                    schlagendeFelder[1] = fp;
                    return felder[x - 2][y + 2];
                }
            }
        } catch (NullPointerException e){

        }

        return fp;
    }

    public void changeStonePosition(){

        boolean geschlagen = geschlagenerStein();

        System.out.println("Neu Feld: " + clickedNewFeld);
        System.out.println("Alt Feld: " + clickedFeld);
        clickedNewFeld.addStone(clickedFeld.getStone());
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if(felder[i][j].equals(clickedNewFeld)){
                    clickedNewFeld.getStone().setX(i);
                    clickedNewFeld.getStone().setY(j);
                }
            }
        }

        checkPossibleQueen();

        try {
            sendPos();
        } catch (Exception e) {
            e.printStackTrace();
        }

        clickedFeld.deleteStone();

        for (Feld feld: possibleFelder){
            if(feld != null)
                feld.setBackBackgroundColor();
        }
        
        clickedFeld.setBackBackgroundColor();

        Arrays.fill(possibleFelder, null);

        clickedFeld = null;
        clickedNewFeld = null;

        System.out.println("GESCHLAGEN: " + geschlagen);

        if(!geschlagen){ // Falls nicht geschlagen wurde, ist der andere Spieler dran
            if(spieler_am_zug == spieler_weiß)
                spieler_am_zug = spieler_schwarz;
            else
                spieler_am_zug = spieler_weiß;
        }

        easyLayout();
    }

    public boolean geschlagenerStein(){

        boolean geschlagen = false;

        for (int i = 0; i < 4; i++) {
//            System.out.println("Difniucvhnfdiuvhnfdoijdiov "+ i  + schlagendeFelder[i]);

            if(possibleFelder[i] != null && possibleFelder[i].equals(clickedNewFeld) && schlagendeFelder[i] != null){
                System.out.println("STEIN WIRD GELÖSCHT: " + schlagendeFelder[i].getStone().getFarbe());
                if(schlagendeFelder[i].getStone().getFarbe().equals(Stein.WHITESTONE)){
                    countwhite--;
                    spieler_am_zug = spieler_schwarz;
                }
                else{
                    countblack--;
                    spieler_am_zug = spieler_weiß;
                }
                schlagendeFelder[i].deleteStone();

                geschlagen = true;
            }

            schlagendeFelder[i] = null;
        }
        return geschlagen;
    }

    public void spielerZug(){

    }

    public void checkPossibleQueen(){
        if(clickedNewFeld.getStone().getFarbe().equals(Stein.BLACKSTONE)){
            if(clickedNewFeld.getStone().getX() == 8 && !clickedNewFeld.getStone().isQueen())
                clickedNewFeld.setToQueen();
        }
        else if(clickedNewFeld.getStone().getFarbe().equals(Stein.WHITESTONE)){
            if(clickedNewFeld.getStone().getX() == 1 && !clickedNewFeld.getStone().isQueen())
                clickedNewFeld.setToQueen();
        }
    }



    public synchronized void easyLayout(){
        // Einfache Darstellung über cmd

        System.out.println("Spieler am Zug: " + spieler_am_zug.getUsername());

        for (Feld[] f1 : felder) {
            System.out.print("\u001B[34m" + "|");
            for(Feld f2 : f1) {
                if(f2 == null){
                    System.out.print(" X ");
                }
                else if(f2.getStone() == null) {
                    System.out.print("   ");
                }
                else {
                    if(f2.getStone().getFarbe().equals(Stein.BLACKSTONE)) {
                        if(f2.getStone().isQueen())
                            System.out.print(" B ");
                        else
                            System.out.print(" b ");
                    }

                    else if(f2.getStone().getFarbe().equals(Stein.WHITESTONE)){
                        if(f2.getStone().isQueen())
                            System.out.print(" W ");
                        else
                            System.out.print(" w ");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("Schwarze Steine: " + countblack + " | Weiße Steine: " + countwhite);
        System.out.println("\u001B[0m");
    }


    private void sendPos() throws Exception{
        InetAddress adr = InetAddress.getByName("127.0.0.1");
        int port = 1234;
        ClientDame client = new ClientDame(adr, port);
        byte[] old = {(byte)clickedFeld.getStone().getX(), (byte) clickedFeld.getStone().getY()};
        byte[] neu = {(byte)clickedNewFeld.getStone().getX(), (byte) clickedNewFeld.getStone().getY()};
        byte[] schlagen = {-1,-1};
        if(schlagendesFeld != null){
            schlagen[0] = (byte)schlagendesFeld.getStone().getX();
            schlagen[1] = (byte)schlagendesFeld.getStone().getY();
        }
        client.sendPositions(old, neu, schlagen);
    }

}