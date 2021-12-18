package modelle;

import java.util.Scanner;

public class GameTest {
	public static void main(String[] args) {
		
		System.out.println(" ===== START =====");
		
		Spielbrett sb = new Spielbrett();
		
		Scanner sn = new Scanner(System.in);
		
		int x, y, xn, yn, direction;
		
		while(true) {
			
			System.out.println(" ===== Stein Bewegen ===== ");

			/*
			System.out.print("Altes Feld x = ");
			x = sn.nextInt();
			System.out.print("Altes Feld y = ");
			y = sn.nextInt();
			System.out.print("Neues Feld x = ");
			xn = sn.nextInt();
			System.out.print("Neues Feld y = ");
			yn = sn.nextInt();

			sb.move(x, y, xn, yn);
			*/
			do{
				System.out.println(" Weiß ");

				System.out.print("Feld x = ");
				x = sn.nextInt();
				System.out.print("Feld y = ");
				y = sn.nextInt();
				System.out.print("Richtung = ");
				direction = sn.nextInt();

			}while(!sb.moveWhite(x,y,direction));

			do{
				System.out.println(" Schwarz ");

				System.out.print("Feld x = ");
				x = sn.nextInt();
				System.out.print("Feld y = ");
				y = sn.nextInt();
				System.out.print("Richtung = ");
				direction = sn.nextInt();

			}while(!sb.moveBlack(x,y,direction));
		}
	}
}
