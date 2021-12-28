package grafiken;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import modelle.Stein;

public class GridLayoutManager extends JFrame{
	
	private Container contents;

	
	private JPanel [][] squares = new JPanel[8][8];
	private JLabel [][] feld = new JLabel[8][8];
	private Stein [][] steine = new Stein[8][8];
	private Color colorBlack = Color.black;
	
	// Current Position
	// Upper left corner of board is (0,0)
//	private int row = 7;
//	private int col = 1;
	
//	private ImageIcon grauerStein = new ImageIcon("images/GrauerStein.png");
//	private ImageIcon beigeStein = new ImageIcon("images/BeigeStein.png");
//	private ImageIcon grauerSteinDame = new ImageIcon("images/GrauerSteinDame.png");
//	private ImageIcon beigeSteinDame = new ImageIcon("images/BeigeSteinDame.png");
	
	public GridLayoutManager() {
		super("Spielbrett");
		
		contents = getContentPane();
		contents.setLayout(new GridLayout(8,8));
		
		contents.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent me) {
		          System.out.println("CLICKED");
		          System.out.println(squares.toString());
		        }
		      });
		
		
		// Create event handler:
//		ButtonHandler buttonHandler = new ButtonHandler();
		
		// Create and add board components:
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new JPanel();
//				squares[i][j].setOpaque(false);
				if ((i + j) % 2 != 0) {
					squares[i][j].setBackground(colorBlack);
				}
				contents.add(squares[i][j]);
				
//				squares[i][j].addActionListener(buttonHandler);
				
			}
		}
		
	//	squares[row][col].setIcon(grauerStein);
	//	squares[0][0].setIcon(grauerStein);
		
		// Setup for black Stones
		int x = 1;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j< 8; j++) {
				if (x % 9 == j) {
//					steine[i][j] = new Stein ("black");
//					feld[i][j] = steine[i][j].getLabel();
//					feld[i][j].setIcon(new Stein("black").getLayout());
	//				feld[i][j].add(steine[i][j].getLabel());
					x += 2;
					if(x % 9 == 8)
						x +=2;
				}
			}
		}
		
		//Setup for white Stones
		for(int i = 5; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(x % 9 == j) {
//					steine[i][j] = new Stein ("white");
//					feld[i][j] = steine[i][j].getLabel();
//					squares[i][j].setIcon(beigeStein);
//					feld[i][j] = new JLabel ();
//					feld[i][j].setIcon(beigeStein);
					x += 2;
					if(x % 9 == 8)
						x += 2;
				}
			}
		}
		
		//Size and display window:
		setSize(1000, 1000);
		setResizable(false);
		setLocationRelativeTo(null);  // Centers window
		setVisible(true);
	}
	
//	private boolean isValidMove(int i, int j) {
//		int rowDelta = Math.abs(i - row);
//		int colDelta = Math.abs(j - col);
//		
//		if ((rowDelta == 1) && 
//			(colDelta == 2)) {
//			return true;
//		}
//		if ((rowDelta == 2) && 
//			(colDelta == 1)) {
//				return true;
//		}
//		return false;
//			
//	}
	
//	private void processClick(int i, int j) {
//		if (isValidMove(i, j) == false) {
//			return;
//		}
//		squares[row][col].setIcon(null);
//		squares[i][j].setIcon(grauerStein);
//		row = i;
//		col = j;
//		
//	}
	
//	private class ButtonHandler implements ActionListener {
//		
//		public void actionPerformed(ActionEvent e) {
//			Object source = e.getSource();
//			
//			for (int i = 0; i < 8; i++) {
//				for (int j = 0; j < 8; j++) {
//					if (source == squares[i][j]) {
//						processClick(i,j);
//						return;
//					}
//				}
//			}
//		}
//
//	}

}
