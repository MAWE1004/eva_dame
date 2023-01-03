package grafiken;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JPanel;


public class SteinGrafik extends JPanel /*implements MouseListener*/{
	private Color farbe;
	private String name;
	public SteinGrafik(Color farbe, String name) {
		this.farbe = farbe;
		setBackground(null);
		setName(name);
		//addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setSize(119, 119);
		setLocation(0, 0);
		g.setColor(farbe);
		int diameter = Math.min(getWidth (),  getHeight() ) -6;
		g.fillOval(3, 3, diameter, diameter);
	}

	/*
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
		System.out.println(this.toString());
		System.out.println(e.getComponent().toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Entered");
		setBackground(new Color(255,255,0, 180));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Exited");
		setBackground(null);
	}
	 */
}
