package controller;

import views_old.BrettGrafik;
import models.Stein;

import javax.swing.*;

public class Game {
    private JPanel grafik;

    private BrettGrafik brett;
    private JPanel[][] felder;
    private Stein[][] steine;

    public Game(){
        Layout();
    }

    public JPanel getBrett(){
        return brett;
    }

    private void Layout() {
        brett = new BrettGrafik();
        felder = brett.getSquares();
        steine = brett.getStones();
    }





    public static void main(String[] args) {
        Game g = new Game();
        JFrame game = new JFrame("Dame");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(972,992);
        game.setResizable(false);
        game.add(g.getBrett());

        game.setVisible(true);
    }
}
