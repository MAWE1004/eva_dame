package grafiken;

import javax.swing.*;

public class Drawingtest extends JPanel {
	public static void main(String[] args) {
		JFrame f = new JFrame("Kreis");
//		SteinGrafik steinPanel = new SteinGrafik();
		BrettGrafik brettPanel = new BrettGrafik();
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		f.add(steinPanel);
		f.add(brettPanel);
		f.setLocation(200, 200);
		f.setSize(300, 300);
		f.setVisible(true);
	}
}
