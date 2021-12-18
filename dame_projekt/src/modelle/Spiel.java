package modelle;

public class Spiel {
	//Attribute
	private double dauer;
	
	private Spieler[] spieler = new Spieler[2];
	private Spielbrett spielbrett;
	
	//const
	public Spiel(Spieler s1, Spieler s2) {
		spielbrett = new Spielbrett();
		
		s1.addSpiel(this);
		s2.addSpiel(this);
		
		spieler[0] = s1;
		spieler[1] = s2;
	}
	
	
}
