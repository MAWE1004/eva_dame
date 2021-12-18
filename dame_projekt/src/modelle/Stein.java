package modelle;

import grafiken.SteinGrafik;

public class Stein {
	//Attribute
	private SteinGrafik layout;
	final private String farbe;
	private boolean queen = false;
	
	public Stein(String farbe) {
		this.farbe = farbe;
	}
	
	public String getFarbe() {
		return farbe;
	}

	public boolean getQueen(){
		return queen;
	}

	public void setQueen(boolean q){
		queen = q;
	}
	
}
