package controller;

import controller.anmeldung.AnmeldungMVC;
import models.Anmeldung;

public class StartMVC {
    public static void main(String[] args) {
        Anmeldung model = new Anmeldung();
        new AnmeldungMVC(model, "Anmeldung");
    }
}
