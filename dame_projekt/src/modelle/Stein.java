package modelle;

import grafiken.SteinGrafik;

public class Stein {
	//Attribute
	private SteinGrafik layout;
	private String farbe;
	private boolean queen = false;
	
	public Stein(String farbe) {
		this.farbe = farbe;
	}
	
	public String getFarbe() {
		return farbe;
	}
	
}
