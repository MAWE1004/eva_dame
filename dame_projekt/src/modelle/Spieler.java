package modelle;

import java.util.ArrayList;

public class Spieler {
	//Attribute
	private String username;
	private String passwort;
	
	private ArrayList spiele = new ArrayList<Spiel>();
	
	private int anzSpiele = 0;
	
	//const
	public Spieler(String username, String passwort) {
		this.username = username;
		this.passwort = passwort;
	}

	
	//Setter, getter
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPasswort() {
		return passwort;
	}
	
	//Methoden
	public void addSpiel(Spiel s) {
		if(anzSpiele == 3) {
			throw new IllegalArgumentException("Es sind bereits 3 Spiele gespeichert");
		}
		
		spiele.add(s);
		anzSpiele = spiele.size();
	}
}
