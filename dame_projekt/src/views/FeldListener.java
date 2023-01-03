package views;

import models.Feld;

public interface FeldListener {
    public void colorChanged(Feld model);
    public void stoneChanged(Feld model);
}
