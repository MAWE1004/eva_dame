package grafiken;

import modelle.Stein;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class BrettGrafik extends JPanel {
	//Colors
	private Color blackStone = new Color(90,90,90);
	private Color whiteStone = new Color(249, 228, 183);

	private JPanel [][] squares = new JPanel[8][8];

	private Component next = null;
	private int[] next_pos = null;

	public BrettGrafik(){
		initGrafik();
	}

	private void initGrafik(){
		setLayout(new GridLayout(8, 8));
		setPreferredSize(new Dimension(972,992));

		for(int i = 0, feld = 1, name = 1; i < 8; i++){
			for (int j = 0; j < 8; j++){
				JPanel p = new JPanel();

				if(feld % 9 == j){
					p.setBackground(Color.BLACK);

					if(i <= 2)
						p.add(new Stein(blackStone,"black_" + name++).getLayout());
					else if(i >= 5)
						p.add(new Stein(whiteStone,"white_" + (name++ - 12)).getLayout());

					p.addMouseListener(new MouseListener() {
						//private boolean clicked = false;
						@Override
						public void mouseClicked(MouseEvent e) {
//                            if(clicked)
//                                p.setBackground(Color.BLACK);
//                            else
//                                p.setBackground(Color.GREEN);
//                            clicked = !clicked;

							System.out.println("Stein auf Feld " + e.getComponent().getComponentAt(0,0).getName());
						}

						@Override
						public void mousePressed(MouseEvent e) {

						}

						@Override
						public void mouseReleased(MouseEvent e) {

						}

						@Override
						public void mouseEntered(MouseEvent e) {

							p.setBackground(Color.RED);
						}

						@Override
						public void mouseExited(MouseEvent e) {

							p.setBackground(Color.BLACK);
						}
					});

					feld+=2;
					if(feld % 9 == 8)
						feld += 2;
				}
				add(p);
			}
		}
	}
}
