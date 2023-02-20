package modelsNeu;

import java.awt.Color;

public class Stein {

    public static final Color BLACKSTONE = new Color(90,90,90);
    public static final Color WHITESTONE = new Color(249, 228, 183);

    //Attribute
    private Color farbe;
    private boolean queen;

    public Stein(Color farbe) {
        this.farbe = farbe;
        this.queen = false;
    }

    public Color getFarbe() {
        return farbe;
    }

    public boolean isQueen(){
        return queen;
    }

    public void setQueen(boolean q){
        this.queen = q;
    }


    @Override
    public String toString() {
        return "Stein {" +
                ", farbe=" + farbe +
                ", queen=" + queen +
                " }";
    }
}