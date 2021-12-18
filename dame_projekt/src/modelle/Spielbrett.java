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
	
	//Bewegen
	public void move(int x, int y, int xn, int yn) {

		feld[xn][yn] = feld[x][y];
		feld[x][y] = null;

		easyLayout(this);
		System.out.println("Bewegegt");

		/*
		// true nach rechts
		// false nach links
		boolean richtung;
		
		while() {
			
		}
		
		
		//�berpr�ft ob begehbar
		if(walkablefield(xn,yn)) {
			do {
				feld[x + 1][y + 1] = feld[x][y];
				
			} while()
			
			feld[xn][yn] = feld[x][y];
			feld[x][y] = null;
			
			easyLayout(this);
			System.out.println("Bewegegt");
		}
		 */
	}
	
	//Stein Schlagen
	private Stein schlagen(int xn, int yn){
		if(xn >= 8 || xn < 0 || yn >= 8 || yn < 0) {
			return feld[xn][yn];
		}
		
		feld[xn][yn] = null;
		
		return schlagen(xn + 1, yn + 1);
	}
	
	
	//feld intizialiesierung
	public void initFeld() {
		
		int x = 1;
		// schwarz 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
					feld[i][j] = new Stein("schwarz");
					x += 2;
					if(x % 9 == 8)
						x += 2;
				}
			}
		}
		
		// wei�
		for(int i = 5; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
					feld[i][j] = new Stein("wei�");
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
					if(f2.getFarbe().equals("schwarz"))
						System.out.print(" s ");
					
					else if(f2.getFarbe().equals("wei�"))
						System.out.print(" w ");
				}
				System.out.print("|");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
