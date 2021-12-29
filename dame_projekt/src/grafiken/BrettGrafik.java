package grafiken;

import modelle.Spielbrett;
import modelle.Stein;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.*;


public class BrettGrafik extends JPanel {

	private JPanel [][] squares = new JPanel[8][8];

	private Component aktuell = null;
	private int[] akt_pos = null;

	public BrettGrafik(){
		initGrafik();
	}

	private void initGrafik(){
		setLayout(new GridLayout(8, 8));

		//Ausgabe aller besetzter Felder
		/*for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				try{
					System.out.println(squares[x][y].getComponent(0).toString());
				}catch(ArrayIndexOutOfBoundsException ex){
					//System.out.println("NullPointer exception");
					//System.out.println(x + " "+ y);
				}
			}
		}
		 */
	}
}
