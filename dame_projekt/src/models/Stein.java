package models;

import java.awt.Color;

public class Stein {

	public static final Color BLACKSTONE = new Color(90,90,90);
	public static final Color WHITESTONE = new Color(249, 228, 183);

	//Attribute
	private Color farbe;
	private String name;
	private boolean queen;
	private int x;//höhe
	private int y;//breite
	
	public Stein(Color farbe, String name, int x, int y) {
		this.farbe = farbe;
		this.name = name;
		this.x = x;
		this.y = y;
		this.queen = false;
	}

	public Color getFarbe() {
		return farbe;
	}

	public String getName() {
		return name;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public boolean isQueen(){
		return queen;
	}

	public void setQueen(boolean q){
		queen = q;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	@Override
	public String toString() {
		return "Stein {" +
				", farbe=" + farbe +
				", name='" + name + '\'' +
				", queen=" + queen +
				", x=" + x +
				", y=" + y +
				" }";
	}
}
