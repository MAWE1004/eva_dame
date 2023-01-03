package modelle_old;

import models.Stein;

import java.awt.*;
import java.util.Scanner;

public class SimpleSpielbrett {
    //Colors
    private Color blackStone = new Color(90,90,90);
    private Color whiteStone = new Color(249, 228, 183);

    private Stein[][] feld = new Stein[8][8];

    //Konstruktor
    public SimpleSpielbrett(){
        init();
        easyLayout();
    }

    //Methoden
    public void moveStone(int x, int y){
        System.out.print("Richtung eingeben: ");
        Scanner in = new Scanner(System.in);
        int direction = in.nextInt();
        Color c = feld[x][y].getFarbe();

        int[] next; // 0 -> x , 1 -> y

        if (feld[x][y].isQueen())
            next = posQueen(x, y, direction);
        else
            next = nextPos(x,y,direction,c);

        try{

        if(fieldFree(next[0],next[1])){
            feld[next[0]][next[1]] = feld[x][y];
            feld[x][y] = null;

            //falls Queen wird
            if(!feld[next[0]][next[1]].isQueen())
                setToQueen(next[0], next[1]);

            easyLayout();
            return;
        }

        int[] toCapture = next;
        if (feld[x][y].isQueen())
            next = posQueen(next[0], next[1], direction);
        else
            next = nextPos(next[0],next[1],direction,c);

        if(fieldFree(next[0],next[1]) && !feld[toCapture[0]][toCapture[1]].getFarbe().equals(c)){
            feld[next[0]][next[1]] = feld[x][y];
            feld[toCapture[0]][toCapture[1]] = null;
            feld[x][y] = null;
        }

        easyLayout();

        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("\u001B[33m" + "Feld nicht vorhanden" + "\u001B[0m");
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
        if(feld[x][y] == null)
            return true;
        return false;
    }

    private void setToQueen(int x, int y){
        if(feld[x][y].getFarbe().equals(blackStone) & x == 7)
            feld[x][y].setQueen(true);
        else if(feld[x][y].getFarbe().equals(whiteStone) & x == 0)
            feld[x][y].setQueen(true);
    }

    //einfaches Layout
    private void easyLayout(){
        // Einfache Darstellung über cmd
		for (Stein[] f1 : feld) {
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
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                //squares[i][j] = new JPanel();
//                //squares[i][j].addMouseListener(brettGrafik);
//                if ((i + j) % 2 != 0) {
//                    //squares[i][j].setBackground(Color.BLACK);
//                    feld[i][j] = new Stein(Color.BLACK, "black1");
//                }
//                //brettGrafik.add(squares[i][j]);
//            }
//        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    feld[i][j] = null;
                }
            }
        }

        int x = 1;
        int counter = 1; //name counter
        // schwarz
        for(int i = 2; i < 3; i++) {
            for(int j = 0; j < 8; j++) {
                if(x % 9 == j) {
//                    feld[i][j] = new Stein(new Color(90,90,90), "black_" + counter++);
//                    feld[i][j].getLayout().addMouseListener(this);
//                    squares[i][j].add(feld[i][j].getLayout());
                    x += 2;
                    if(x % 9 == 8)
                        x += 2;
                }
            }
        }

        counter = 1;
        // weiss
        for(int i = 5; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                if(x % 9 == j) {
//                    feld[i][j] = new Stein(new Color(249, 228, 183), "white_" + counter++);
//                    feld[i][j].getLayout().addMouseListener(this);
//                    squares[i][j].add(feld[i][j].getLayout());
                    x += 2;
                    if(x % 9 == 8)
                        x += 2;
                }
            }
        }

//        aktuell = squares[0][0];
//        next = squares[0][0];
//        last = squares[0][0];
//
//        walkablefieldinit();
    }
}
