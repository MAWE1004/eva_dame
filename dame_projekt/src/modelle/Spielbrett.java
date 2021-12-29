package modelle;

import grafiken.BrettGrafik;
import grafiken.GameGrafikStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class Spielbrett implements MouseListener {
	//Attribute
	
	private Stein[][] feld = new Stein[8][8];
	private JPanel[][] squares = new JPanel[8][8];
	private BrettGrafik brettGrafik;
	private Component aktuell = null;
	private int[] akt_pos = null;
	private int[] next_pos = null;
	
	//nutzbare Felder
	private Map<Stein, Boolean> felder = new HashMap<>();
	
	//const
	public Spielbrett() {
		brettGrafik = new BrettGrafik();
		initFeld();
		easyLayout(this);
	}

	//Methoden
	public Stein[][] getFeld() {
		return feld;
	}

	public BrettGrafik getBrettGrafik(){
		return brettGrafik;
	}
	
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
			//easyLayout(this);
			return false;
		}


		feld[xn][yn] = feld[x][y];
		feld[xb][yb] = null;
		feld[x][y] = null;

		toQueen(xn, yn);

		//easyLayout(this);
		System.out.println("Stein auf Feld X = " + xb + " Y = " +  yb + " wurde geschlagen");
		return true;
	}

	private void toQueen(int x, int y){
		if(x == 0 | x == 7){
			feld[x][y].setQueen(true);
		}
	}
	//Anzeige welche Felder begehbar
	private int[] movableTo() {
		int[] nFelder = {-1,-1,-1,-1};// Bei Überschreiten des Array index, damit bewusst ist, dass dieser Wert nicht korrekt ist
		if(feld[akt_pos[0]][akt_pos[1]].getName().charAt(0) == 'b'){
			if(fieldEmpty(akt_pos[0]+1,akt_pos[1]-1)){
				nFelder[0] = akt_pos[0]+1; // links
				nFelder[1] = akt_pos[1]-1;
			}
			if(fieldEmpty(akt_pos[0]+1,akt_pos[1]+1)){
				nFelder[2] = akt_pos[0]+1; // rechts
				nFelder[3] = akt_pos[1]+1;
			}

			return nFelder;
		}
		//white_
		if(fieldEmpty(akt_pos[0]-1,akt_pos[1]-1)){
			nFelder[0] = akt_pos[0]-1; // links
			nFelder[1] = akt_pos[1]-1;
		}
		if(fieldEmpty(akt_pos[0]-1,akt_pos[1]+1)){
			nFelder[2] = akt_pos[0]-1; // rechts
			nFelder[3] = akt_pos[1]+1;
		}
		return nFelder;
	}

	private boolean fieldEmpty(int x, int y){
		try{
			if(feld[x][y] == null){// schauen was passiert, wenn am Ende des Feldes ist und Dame werden müsste
				return true;
			}
			// falls nächstes Feld besetzt und übernächstes Feld frei um zu schlagen
			if(feld[x][y].getName().charAt(0) != aktuell.getName().charAt(0)){
				//return fieldEmpty()
			}

		}catch(ArrayIndexOutOfBoundsException ex){ // Fängt ab, damit es nicht zum absturz kommt, wenn der Index gewollt nicht vorhanden ist
			System.err.println("Array Größe überschritten");
		}

		return false;
	}

	//feld intizialiesierung
	public void initFeld() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new JPanel();
				if ((i + j) % 2 != 0) {
					squares[i][j].setBackground(Color.BLACK);
				}
				brettGrafik.add(squares[i][j]);
			}
		}
		
		int x = 1;
		int counter = 1; //name counter
		// schwarz 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
					feld[i][j] = new Stein(new Color(90,90,90), "black_" + counter++);
					feld[i][j].getLayout().addMouseListener(this);
					squares[i][j].add(feld[i][j].getLayout());
					x += 2;
					if(x % 9 == 8)
						x += 2;
				}
			}
		}

		counter = 1;
		// weiss
		for(int i = 5; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
					feld[i][j] = new Stein(new Color(249, 228, 183), "white_" + counter++);
					feld[i][j].getLayout().addMouseListener(this);
					squares[i][j].add(feld[i][j].getLayout());
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
		GameGrafikStart game_visual = new GameGrafikStart(this);
		/* // Einfache Darstellung über cmd
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
		 */
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
		aktuell = e.getComponent();
		//System.out.println("Aktuell : " + aktuell.toString());
		akt_pos = findPos(aktuell);
		System.out.println("X = " + akt_pos[0] + "Y = " + akt_pos[1]);

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Entered");
		aktuell = e.getComponent();
		akt_pos = findPos(aktuell);
		next_pos = movableTo();
		aktuell.setBackground(new Color(255,255,0, 180));
		if(next_pos[0] >= 0)
			squares[next_pos[0]][next_pos[1]].setBackground(new Color(255,255,0, 180));
		if(next_pos[2] >= 0)
			squares[next_pos[2]][next_pos[3]].setBackground(new Color(255,255,0, 180));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Exited");
		aktuell = e.getComponent();
		aktuell.setBackground(null);
		if(next_pos[0] >= 0)
			squares[next_pos[0]][next_pos[1]].setBackground(Color.BLACK);
		if(next_pos[2] >= 0)
			squares[next_pos[2]][next_pos[3]].setBackground(Color.BLACK);
	}

	private int[] findPos(Component obj){
		int x = -1, y = -1;
		int[] n = new int[2];
		//Schleife
		for(x = 0; x < 8; x++){
			for(y = 0; y < 8; y++){
				try{
					if(feld[x][y].getLayout().equals(obj)){
						System.out.println("Gefunden : " + squares[x][y].getComponent(0));
						n[0] = x;
						n[1] = y;
						return n;
					}
				}catch(NullPointerException ex){
					//System.out.println("NullPointer exception");
					//System.out.println(x + " "+ y);
				}
			}
		}
		n[0] = x;
		n[1] = y;
		return n;
	}
}
