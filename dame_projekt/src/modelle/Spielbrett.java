package modelle;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Spielbrett {
	//Attribute
	
	private Stein[][] feld = new Stein[8][8];//sp�ter Graphik
	
	//nutzbare Felder
	private Map<Stein, Boolean> felder = new HashMap<>();
	
	//const
	public Spielbrett() {
		initFeld();
		easyLayout(this);
	}
	
	
	//Methoden
	
	//begehbare Felder init
	private void walkablefieldinit() {
		int x = 1;
		// schwarz 
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
					felder.put(feld[i][j], true);
					x += 2;
					if(x % 9 == 8)
						x += 2;
				} 
				else {
					felder.put(feld[i][j], false);
				}
			}
		}
	}
	
	//�berpr�fen ob Feld begehbar
	private boolean walkablefield(int x, int y) {
		if(feld[x][y] != null & felder.get(feld[x][y])) {
			return true;
		}
		
		if(feld[x][y] == null) {
			System.out.println("Kein Stein Vorhanden");
			return false;
		}
			
		
		System.out.println("Kein begehbares Feld");
		return false;
	}
	
	//Bewegen schwarz
	public boolean moveWhite(int x, int y, int direction){
		//QueenCheck
		try{
			if(feld[x][y].getQueen()){
				return moveQueen(x, y, direction);
			}


			if(direction == 0 & y - 1 >= 0) {
				if(feld[x - 1][y - 1] != null){
					System.out.println("Feld besetzt");
					return beat(x, y, x-1, y-1, x-2, y-2);
				}
				feld[x - 1][y - 1] = feld[x][y];
				feld[x][y] = null;
				toQueen(x, y);
				System.out.println("Bewegt");
				easyLayout(this);
				return true;

			}
			else if(direction == 1 & y + 1 <= 7){
				if(feld[x - 1][y + 1] != null){
					System.out.println("Feld besetzt");
					return beat(x, y, x-1, y+1, x-2, y+2);
				}
				feld[x - 1][y + 1] = feld[x][y];
				feld[x][y] = null;
				toQueen(x, y);
				System.out.println("Bewegt");
				easyLayout(this);
				return true;
			}
		}catch(Exception e){
			System.out.println("Ein Fehler ist aufgetreten");
			e.printStackTrace();
		}


		easyLayout(this);
		return false;
	}

	public boolean moveBlack(int x, int y, int direction){
		//QueenCheck
		try{
			if(feld[x][y].getQueen()){
				return moveQueen(x, y, direction);
			}

			// 0 = left
			// 1 = right
			if(direction == 0 & y - 1 >= 0) {
				if(feld[x + 1][y - 1] != null){
					System.out.println("Feld besetzt");
					return beat(x, y, x+1, y-1, x+2, y-2);
				}
				feld[x + 1][y - 1] = feld[x][y];
				feld[x][y] = null;
				toQueen(x, y);
				System.out.println("Bewegt");
				easyLayout(this);
				return true;

			}
			else if(direction == 1 & y + 1 <= 7){
				if(feld[x + 1][y + 1] != null){
					System.out.println("Feld besetzt");
					return beat(x, y, x+1, y+1, x+2, y+2);
				}
				feld[x + 1][y + 1] = feld[x][y];
				feld[x][y] = null;
				toQueen(x, y);
				System.out.println("Bewegt");
				easyLayout(this);
				return true;
			}
		}catch(Exception e){
			System.out.println("Ein Fehler ist aufgetreten");
			e.printStackTrace();
		}


		easyLayout(this);
		return false;
	}

	public boolean moveQueen(int x, int y, int direction) {
		if (direction == 7 & y - 1 >= 0) {
			if(feld[x - 1][y - 1] != null){
				System.out.println("Feld besetzt");
				return beat(x, y, x-1, y-1, x-2, y-2);
			}
			feld[x - 1][y - 1] = feld[x][y];
			feld[x][y] = null;
			System.out.println("Bewegt");
			easyLayout(this);
			return true;
		} else if (direction == 9 & y + 1 <= 7) {
			if(feld[x - 1][y + 1] != null){
				System.out.println("Feld besetzt");
				return beat(x, y, x-1, y+1, x-2, y+2);
			}
			feld[x - 1][y + 1] = feld[x][y];
			feld[x][y] = null;
			System.out.println("Bewegt");
			easyLayout(this);
			return true;
		} else if (direction == 1 & y - 1 >= 0) {
			if(feld[x + 1][y - 1] != null){
				System.out.println("Feld besetzt");
				return beat(x, y, x+1, y-1, x+2, y-2);
			}
			feld[x + 1][y - 1] = feld[x][y];
			feld[x][y] = null;
			System.out.println("Bewegt");
			easyLayout(this);
			return true;
		} else if (direction == 3 & y + 1 <= 7) {
			if(feld[x + 1][y + 1] != null){
				System.out.println("Feld besetzt");
				return beat(x, y, x+1, y+1, x+2, y+2);
			}
			feld[x + 1][y + 1] = feld[x][y];
			feld[x][y] = null;
			System.out.println("Bewegt");
			easyLayout(this);
			return true;
		}

		return false;
	}

	//Stein Schlagen
	private boolean beat(int x, int y, int xb, int yb, int xn, int yn){
		//nächtses Feld besetzt
		//übernächstes Feld frei
		if(feld[xn][yn] != null){
			easyLayout(this);
			return false;
		}


		feld[xn][yn] = feld[x][y];
		feld[xb][yb] = null;
		feld[x][y] = null;

		toQueen(xn, yn);

		easyLayout(this);
		System.out.println("Stein auf Feld X = " + xb + " Y = " +  yb + " wurde geschlagen");
		return true;
	}

	private void toQueen(int x, int y){
		if(x == 0 | x == 7){
			feld[x][y].setQueen(true);
		}
	}

	//feld intizialiesierung
	public void initFeld() {
		
		int x = 1;
		// schwarz 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
					feld[i][j] = new Stein(Color.BLACK);
					x += 2;
					if(x % 9 == 8)
						x += 2;
				}
			}
		}
		
		// weiss
		for(int i = 5; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
					feld[i][j] = new Stein(Color.WHITE);
					x += 2;
					if(x % 9 == 8)
						x += 2;
				}
			}
		}
		
		walkablefieldinit();
	}
	
	//einfache Spielbrett darstellung
	public void easyLayout(Spielbrett s) {
		
		for (Stein[] f1 : feld) {
			System.out.print("|");
			for(Stein f2 : f1) {
				if(f2 == null) {
					System.out.print("   ");
				}
				else {
					if(f2.getFarbe().equals(Color.BLACK)) {
						if(f2.getQueen())
							System.out.print(" B ");
						else
							System.out.print(" b ");
					}
					
					else if(f2.getFarbe().equals(Color.WHITE)){
						if(f2.getQueen())
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
}