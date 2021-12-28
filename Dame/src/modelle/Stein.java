package modelle;

import java.awt.Color;
import grafiken.SteinGrafik;

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
	
	public SteinGrafik getLayout() {
		return layout;
	}	
}