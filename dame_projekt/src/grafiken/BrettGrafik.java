package grafiken;

import java.awt.*;

import javax.swing.*;

public class BrettGrafik extends JPanel {
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		
		g.fillRect(3, 3, 100, 100);
		
//		int diameter = Math.min(getWidth (),  getHeight() ) -6;
//		g.fillOval(3, 3, diameter, diameter);
		
	}
}
