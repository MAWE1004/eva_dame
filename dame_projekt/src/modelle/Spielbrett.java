package modelle;

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
	public boolean moveBlack(int x, int y, int direction){
		if(direction == 0 & y - 1 >= 0) {
			if(feld[x - 1][y - 1] != null){
				System.out.println("Feld besetzt");
				// schlagen
				easyLayout(this);
				return false;
			}
			feld[x - 1][y - 1] = feld[x][y];
			feld[x][y] = null;
			System.out.println("Bewegt");
			easyLayout(this);
			return true;

		}
		else if(direction == 1 & y + 1 <= 7){
			if(feld[x - 1][y + 1] != null){
				System.out.println("Feld besetzt");
				//schlagen
				easyLayout(this);
				return false;
			}
			feld[x - 1][y + 1] = feld[x][y];
			feld[x][y] = null;
			System.out.println("Bewegt");
			easyLayout(this);
			return true;
		}

		//Queen Check

		easyLayout(this);

		return false;
	}

	//Bewegen schwarz
	public boolean moveWhite(int x, int y, int direction){
		// 0 = left
		// 1 = right
		if(direction == 0 & y - 1 >= 0) {
			if(feld[x + 1][y - 1] != null){
				System.out.println("Feld besetzt");
				//schlagen
				easyLayout(this);
				return false;
			}
			feld[x + 1][y - 1] = feld[x][y];
			feld[x][y] = null;
			System.out.println("Bewegt");
			easyLayout(this);
			return true;

		}
		else if(direction == 1 & y + 1 <= 7){
			if(feld[x + 1][y + 1] != null){
				System.out.println("Feld besetzt");
				//schlagen
				easyLayout(this);
				return false;
			}
			feld[x + 1][y + 1] = feld[x][y];
			feld[x][y] = null;
			System.out.println("Bewegt");
			easyLayout(this);
			return true;
		}

		//Queen Check

		easyLayout(this);

		return false;
	}
	
	//Stein Schlagen
	private void beat(int x, int y, int xn, int yn){
	}
	
	
	//feld intizialiesierung
	public void initFeld() {
		
		int x = 1;
		// schwarz 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
					feld[i][j] = new Stein("black");
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
					feld[i][j] = new Stein("white");
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
					if(f2.getFarbe().equals("black"))
						System.out.print(" b ");
					
					else if(f2.getFarbe().equals("white"))
						System.out.print(" w ");
				}
				System.out.print("|");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
