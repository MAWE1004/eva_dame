package grafiken;

import java.awt.*;

import javax.swing.JPanel;


public class SteinGrafik extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setSize(300, 300);
		setBackground(null);
		g.setColor(Color.BLUE);

		int diameter = Math.max(getWidth (),  getHeight() ) -6;
		g.fillOval(3, 3, diameter, diameter);
	}

}
