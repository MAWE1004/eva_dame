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


    }
}
