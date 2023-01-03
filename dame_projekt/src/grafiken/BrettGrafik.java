package grafiken;

import modelle.Stein;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.*;


public class BrettGrafik extends JPanel{

	private JPanel [][] squares = new JPanel[8][8];
	private Stein[][] feld = new Stein [8][8];

	public BrettGrafik(){
		initGrafik();
	}

	private void initGrafik(){
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new JPanel();
				if ((i + j) % 2 != 0) {
					squares[i][j].setBackground(Color.BLACK);
				}
				add(squares[i][j]);
			}
		}
		//schwarz
		int x = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (x % 9 == j) {
					feld[i][j] = new Stein(new Color(90,90,90));
					feld[i][j].getLayout();
					squares[i][j].add(feld[i][j].getLayout());
					x += 2;
					if (x % 9 == 8)
						x += 2;
				}
			}
		}
		//weiss
		for (int i = 5; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (x % 9 == j) {
					feld[i][j] = new Stein(new Color(249, 228, 183));
					feld[i][j].getLayout();
					squares[i][j].add(feld[i][j].getLayout());
					x += 2;
					if (x % 9 == 8)
						x += 2;

				}
			}
		}
	}
}
