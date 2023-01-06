package models;

import socket.ClientMultiDame;
import socket.SendZug;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

public class Brett{
    private static int countGame = 1;

    private ClientMultiDame clientMultiDame = null;
    private byte[] sendOld;
    private byte[] sendNeu;
    private byte[] sendSchlagen;

    private Feld[][] felder;
    private Feld clickedFeld;
    private Feld clickedNewFeld;
    private Feld schlagendesFeld;
    private Feld[] possibleFelder;
    private Feld[] schlagendeFelder;

    private boolean myTurn;
    private Color farbe;

    private String spieler_schwarz;
    private String spieler_weiß;
    private String spieler_am_zug;

    private GameInfo gameInfo;

    private byte countblack;
    private byte countwhite;


    public Brett(long timeInMin,String schwarz, String weiß, InetAddress group, int port) throws IOException {
//        super("Brett " + countGame++);
        myTurn = true;
        farbe = Stein.BLACKSTONE;
        spieler_schwarz = schwarz;
        spieler_weiß = weiß;
        spieler_am_zug = schwarz;
        this.clientMultiDame = new ClientMultiDame(group, port);
        gameInfo = new GameInfo(timeInMin,schwarz,weiß,"schwarz", "weiß");
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
    public Brett(long timeInMin,String schwarz, String weiß){
//        super("Brett " + countGame++);

        spieler_schwarz = schwarz;
        spieler_weiß = weiß;
        spieler_am_zug = schwarz;
        gameInfo = new GameInfo(timeInMin,schwarz,weiß,"schwarz", "weiß");
        felder = new Feld[10][10];
        clickedFeld = null;
        clickedNewFeld = null;
        schlagendesFeld = null;
        possibleFelder = new Feld[]{null,null,null,null}; // 0 und 1 für weiß, 2 und 3 für schwarz, 0-3 für dame
        schlagendeFelder= new Feld[]{null,null,null,null};
        countblack = 12;
        countwhite = 12;

        initializeBrett();
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

    public String getSpieler_am_zug() {
        return spieler_am_zug;
    }

    public String getSpieler_schwarz() {
        return spieler_schwarz;
    }

    public String getSpieler_weiß() {
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

    public boolean isMyTurn() {
        return myTurn;
    }

    public Color getFarbe() {
        return farbe;
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
                    sendNeu = new byte[]{(byte) i, (byte) j};
                }
            }
        }

        checkPossibleQueen();

        System.out.println(" ===== Felder =====");
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if(felder[i][j].equals(clickedFeld)){
                    System.out.println("Alt X: " + i + " Alt Y: " + j);
                    sendOld = new byte[]{(byte) i, (byte) j};
                }
                if(felder[i][j].equals(clickedNewFeld)){
                    System.out.println("Neu X: " + i + " Neu Y: " + j);
                    sendNeu = new byte[]{(byte) i, (byte) j};
                }
                if(felder[i][j].equals(schlagendesFeld)){
                    System.out.println("Schlagen X: " + i + " Schlagen Y: " + j);
                    sendSchlagen = new byte[]{(byte) i, (byte) j};
                }
            }
        }

        try {
            sendPos();
            receivePos();
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

//        if(!geschlagen){ // Falls nicht geschlagen wurde, ist der andere Spieler dran
//            if(spieler_am_zug == spieler_weiß)
//                spieler_am_zug = spieler_schwarz;
//            else
//                spieler_am_zug = spieler_weiß;
//        }

        if(!geschlagen){ // Falls nicht geschlagen wurde, ist der andere Spieler dran
            myTurn = !myTurn;
        }

        easyLayout();

        if(!myTurn){ // Falls nicht am Zug, warte auf Gegner
            try {
//                receivePos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public boolean geschlagenerStein(){

        boolean geschlagen = false;

        for (int i = 0; i < 4; i++) {

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
                schlagendesFeld = schlagendeFelder[i];

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

        System.out.println("Spieler am Zug: " + spieler_am_zug);

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

    private void changeGegnerStonePos(SendZug data){
        Feld alt = felder[data.getAltX()][data.getAltY()];
        Feld neu = felder[data.getNeuX()][data.getNeuY()];
        Feld schlagen;
        try{
            schlagen = felder[data.getSchlagenX()][data.getSchlagenY()];
        } catch (ArrayIndexOutOfBoundsException e){
            schlagen = null;
        }


        neu.addStone(alt.getStone());
        alt.deleteStone();
        if(schlagen != null)
            schlagen.deleteStone();

        easyLayout();
    }


    private void sendPos() throws Exception{
        if(schlagendesFeld == null){
            sendSchlagen = new byte[]{-1,-1};
        }

        clientMultiDame.sendPos(String.valueOf(gameInfo.getUserStein().charAt(0)), sendOld, sendNeu, sendSchlagen);
    }

    private void receivePos() throws Exception{
        SendZug response = clientMultiDame.receivePos();
        if(!myTurn){
            changeGegnerStonePos(response);
        }
    }

}
