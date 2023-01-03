package controller;

import models.Brett;

import javax.swing.*;

public class DameMVC extends JFrame {
    public DameMVC(Brett model, String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(972,992);
        setResizable(false);


        setVisible(true);
    }

    public static void main(String[] args) {
//        Brett brettModel = new Brett();
//        new DameMVC(brettModel, "Dame Spiel 1");
    }
}
