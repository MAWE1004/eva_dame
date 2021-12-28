package grafiken;


import javax.swing.*;

public class Drawingtest extends JPanel{
	public static void main(String[]args) {
		
		JFrame f = new JFrame("Spielbrett");
		BrettGrafik brettPanel = new BrettGrafik();
//		SteinGrafik steinPanel = new SteinGrafik();
//		GridLayoutManager grid = new GridLayoutManager();
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocation(0, 0);
		f.setSize(1000, 1000);
//		f.setLayout(new GridLayout(8, 8));
		f.add(brettPanel);
		f.setResizable(false);
		f.setVisible(true);
	//	f.add(steinPanel);
	//	grid.pack();

		
		
		
//		grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		grid.setVisible(true);
		
		
		
//		JFrame g = new JFrame("Stein");
		
//		g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		g.add(steinPanel);
//		g.setLocation(0, 0);
//		g.setSize(800, 800);
//		g.setVisible(true);
		
	}
}

