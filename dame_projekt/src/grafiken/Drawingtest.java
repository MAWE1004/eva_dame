package grafiken;

import modelle.Stein;

import javax.swing.*;
import java.awt.*;

public class Drawingtest extends JPanel {
	public static void main(String[] args) {
		JFrame game = new JFrame("Dame");
		//JPanel m = new JPanel();
		//m.setBounds(0, 0, 1000, 1000);

		BrettGrafik brett = new BrettGrafik();
		//m.add(brett.getGrafik());

		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(972,992);
		game.add(brett);
		//game.add(m);

		game.setVisible(true);
	}
}
