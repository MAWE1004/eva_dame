package controller;

import modelle_old.SimpleSpielbrett;

import java.util.Scanner;

public class SimpleGame {
    public static void main(String[] args) {

        System.out.println(" ===== START =====");

        SimpleSpielbrett sb = new SimpleSpielbrett();
		Scanner sn = new Scanner(System.in);

		int x, y;

        while(true) {
            System.out.println(" ===== Stein Bewegen ===== ");

            System.out.print("Feld x = ");
            x = sn.nextInt();
            System.out.print("Feld y = ");
            y = sn.nextInt();

            sb.moveStone(x,y);
        }


//		while(true) {
//
//			System.out.println(" ===== Stein Bewegen ===== ");
//
//			do{
//				System.out.println(" Weiß ");
//
//				System.out.print("Feld x = ");
//				x = sn.nextInt();
//				System.out.print("Feld y = ");
//				y = sn.nextInt();
//				System.out.print("Richtung = ");
//				direction = sn.nextInt();
//
//			}while(!sb.moveWhite(x,y,direction));
//
//			do{
//				System.out.println(" Schwarz ");
//
//				System.out.print("Feld x = ");
//				x = sn.nextInt();
//				System.out.print("Feld y = ");
//				y = sn.nextInt();
//				System.out.print("Richtung = ");
//				direction = sn.nextInt();
//
//			}while(!sb.moveBlack(x,y,direction));
//		}
    }
}
