package modelle_old;

import models.Stein;
import views_old.BrettGrafik;

import javax.swing.*;
import java.awt.*;

public class MainSpielbrett {
    //Colors
    private Color blackStone = new Color(90,90,90);
    private Color whiteStone = new Color(249, 228, 183);

    private BrettGrafik brett;
    private JPanel[][] felder;
    private Stein[][] steine;

    //Konstruktor
    public MainSpielbrett(){
        init();
        easyLayout();
    }

    //Methoden
    public void moveStone(int x, int y, int direction){
        Color c = steine[x][y].getFarbe();

        int[] next; // 0 -> x , 1 -> y

        if (steine[x][y].isQueen())
            next = posQueen(x, y, direction);
        else
            next = nextPos(x,y,direction,c);

        try{

            if(fieldFree(next[0],next[1])){
                steine[next[0]][next[1]] = steine[x][y];
                steine[x][y] = null;

                //falls Queen wird
                if(!steine[next[0]][next[1]].isQueen())
                    setToQueen(next[0], next[1]);

                easyLayout();
                return;
            }

            int[] toCapture = next;
            if (steine[x][y].isQueen())
                next = posQueen(next[0], next[1], direction);
            else
                next = nextPos(next[0],next[1],direction,c);

            if(fieldFree(next[0],next[1]) && !steine[toCapture[0]][toCapture[1]].getFarbe().equals(c)){
                steine[next[0]][next[1]] = steine[x][y];
                steine[toCapture[0]][toCapture[1]] = null;
                steine[x][y] = null;
            }

            easyLayout();

        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("\u001B[33m" + "steine nicht vorhanden" + "\u001B[0m");
        }

    }

    private int[] posQueen(int x, int y, int direction){
        int n[] = new int[2];

        switch(direction){
            case 1:
                n[0] = x+1;
                n[1] = y-1;
                break;
            case 3:
                n[0] = x+1;
                n[1] = y+1;
                break;
            case 7:
                n[0] = x-1;
                n[1] = y-1;
                break;
            case 9:
                n[0] = x-1;
                n[1] = y+1;
                break;
            default:
        }

        return n;
    }

    private int[] nextPos(int x, int y, int direction, Color c){
        int n[] = new int[2];

        //move left
        if(direction == 4){
            if(c.equals(blackStone)){
                n[0] = x+1;
            }
            else{
                n[0] = x-1;
            }
            n[1] = y-1;
        }
        //move right
        else if(direction == 6){
            if(c.equals(blackStone)){
                n[0] = x+1;
            }
            else{
                n[0] = x-1;
            }
            n[1] = y+1;
        }

        return n;
    }

    private boolean fieldFree(int x, int y){
        if(steine[x][y] == null)
            return true;
        return false;
    }

    private void setToQueen(int x, int y){
        if(steine[x][y].getFarbe().equals(blackStone) & x == 7)
            steine[x][y].setQueen(true);
        else if(steine[x][y].getFarbe().equals(whiteStone) & x == 0)
            steine[x][y].setQueen(true);
    }

    //einfaches Layout
    private void easyLayout(){
        // Einfache Darstellung über cmd
        for (Stein[] f1 : steine) {
            System.out.print("|");
            for(Stein f2 : f1) {
                if(f2 == null) {
                    System.out.print("   ");
                }
                else {
                    if(f2.getFarbe().equals(blackStone)) {
                        if(f2.isQueen())
                            System.out.print(" B ");
                        else
                            System.out.print(" b ");
                    }

                    else if(f2.getFarbe().equals(whiteStone)){
                        if(f2.isQueen())
                            System.out.print(" W ");
                        else
                            System.out.print(" w ");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }

        System.out.println();
    }

    //Spielbrett Initialisieren
    private void init(){
        brett = new BrettGrafik();
        felder = brett.getSquares();
        steine = brett.getStones();
    }
}
