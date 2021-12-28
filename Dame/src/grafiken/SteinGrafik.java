package grafiken;

import java.awt.*;

import javax.swing.JPanel;


public class SteinGrafik extends JPanel {
	
	Color farbe;
	
	public SteinGrafik(Color farbe) {
		this.farbe = farbe;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setSize(120, 120);
		setBackground(null);
		setLocation(0, 0);
		g.setColor(farbe);

		int diameter = Math.min(getWidth (),  getHeight() ) -6;
		g.fillOval(3, 3, diameter, diameter);
	}

}