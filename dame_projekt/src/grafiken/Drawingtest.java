package grafiken;

import modelle.Stein;

import javax.swing.*;
import java.awt.*;

public class Drawingtest extends JPanel {
	public static void main(String[] args) {
		JFrame test = new JFrame("Test");
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		test.setLayout(new GridLayout(0, 1));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setBackground(Color.black);

		//Stein
		SteinGrafik stein = new SteinGrafik();
		SteinGrafik stein2 = new SteinGrafik();
		p1.add(stein);
		p2.add(stein2);
		test.add(p1);
		test.add(p2);

		test.setSize(1000,1000);

		test.setVisible(true);
//		SteinGrafik steinPanel = new SteinGrafik();
//		BrettGrafik brettPanel = new BrettGrafik();

//		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		f.add(steinPanel);
//		f.add(brettPanel);
//		f.setLocation(200, 200);
//		f.setSize(300, 300);
//		f.setVisible(true);


//		GridLayout layout = new GridLayout(8,8);
	}
}
