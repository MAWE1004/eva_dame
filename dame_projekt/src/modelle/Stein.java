package modelle;

import grafiken.SteinGrafik;

import java.awt.Color;

public class Stein {
	//Attribute
	private SteinGrafik layout;
	private Color farbe;
	private boolean queen;
	
	public Stein(Color farbe) {
		this.farbe = farbe;
		this.layout = new SteinGrafik(farbe);
		this.queen = false;
	}
	
	public Color getFarbe() {
		return farbe;
	}

	public boolean getQueen(){
		return queen;
	}

	public void setQueen(boolean q){
		queen = q;
	}

	public SteinGrafik getLayout(){
		return layout;
	}
	
}
