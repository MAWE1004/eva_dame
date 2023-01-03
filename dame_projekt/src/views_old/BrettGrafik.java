package views_old;

import models.Stein;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class BrettGrafik extends JPanel{
	//Colors
	private Color blackStone = new Color(90,90,90);
	private Color whiteStone = new Color(249, 228, 183);
	private Color backgroundYellow = new Color(255,255,0, 180);
	//private Color transparent = new Color(0,0,0,0);

	private JPanel [][] squares;
	private Stein [][] stones;

	private Component next = null;
	private int[] next_pos = null;

	private Component stone_on_field = null;
	private JPanel clicked_field_old = null;

	public BrettGrafik(){
		squares = new JPanel[8][8];
		stones = new Stein[8][8];
		initGrafik();
	}

	public JPanel[][] getSquares(){
		return squares;
	}

	public Stein[][] getStones(){
		return stones;
	}

	private void initGrafik(){
		setLayout(new GridLayout(8, 8));
		setMaximumSize(new Dimension(972,992));

		for(int i = 0, feld = 1, name = 1; i < 8; i++){
			for (int j = 0; j < 8; j++){
				JPanel p = new JPanel();

				if(feld % 9 == j){
					p.setBackground(Color.BLACK);

//					if(i <= 2){ // BLACK STONES
//						stones[i][j] = new Stein(blackStone, "black_" + name++);
//						stones[i][j].setX(i);
//						stones[i][j].setY(j);
//						p.add(stones[i][j].getLayout());
//					}
//					else if(i >= 5){ // WHITE STONES
//						stones[i][j] = new Stein(whiteStone,"white_" + (name++ - 12));
//						stones[i][j].setX(i);
//						stones[i][j].setY(j);
//						p.add(stones[i][j].getLayout());
//					}

					p.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {

							try {
								//Background neues Feld erkennbar machen
								stone_on_field = p.getComponentAt(0, 0);
								p.setBackground(backgroundYellow);


								//Background altes Feld leeren
								if (clicked_field_old != null) {
									clicked_field_old.setBackground(Color.BLACK);
								}

								//Stein finden
								int x = 0, y = 0;

								search:
								for (x = 0; x < 8; ++x) {
									for (y = 0; y < 8; ++y) {
										if (squares[x][y].getBackground().equals(backgroundYellow))
											break search;
									}
								}
								Stein stone = stones[x][y];

								System.out.println(stone.getName());
								System.out.println("Position -> Horizontal " + stone.getX() + ", Vertikal " + stone.getY());
								clicked_field_old = p;
							} catch (NullPointerException exception){
								p.setBackground(Color.BLACK);
								System.err.println("Feld besitzt keinen Stein => anderes Feld MIT Stein auswählen");
							} catch (Exception exception){
								exception.printStackTrace();
							}
						}

						@Override
						public void mousePressed(MouseEvent e) {
						}

						@Override
						public void mouseReleased(MouseEvent e) {
						}

						@Override
						public void mouseEntered(MouseEvent e) {
						}

						@Override
						public void mouseExited(MouseEvent e) {
						}
					});

					feld+=2;
					if(feld % 9 == 8)
						feld += 2;
				}

				squares[i][j] = p;
				add(p);
			}
		}
	}

	public Component getNext() {
		return next;
	}
}
