package modelle;

import grafiken.SteinGrafik;

import java.awt.Color;

public class Stein {
	//Attribute
	private SteinGrafik layout;
	private Color farbe;
	private String name;
	private boolean queen;
	
	public Stein(Color farbe, String name) {
		this.farbe = farbe;
		this.name = name;
		this.layout = new SteinGrafik(farbe, name);
		this.queen = false;
	}

	public SteinGrafik getLayout(){
		return layout;
	}

	public Color getFarbe() {
		return farbe;
	}

	public String getName() {
		return name;
	}

	public boolean getQueen(){
		return queen;
	}

	public void setQueen(boolean q){
		queen = q;
	}


	
}
