package controller;

import javax.swing.*;

public class TutorialMVC extends JFrame {

    public TutorialMVC(String title){
        super(title);
        setSize(700,650);
        setResizable(false);

        JLabel label = new JLabel();
        label.setText("<html><h1>Tutorial:</h1>" + "<br/>" + "<h2>Menü:</h2>"
                +"Nach der Anmeldung/Registrierung kann man im Menü aussuchen, ob man ein 5 / 10 / 30 Minuten Spiel machen möchte. Drückt man also auf eine der Buttons mit 5 min / 10 min / 30 min, so sucht man ein Spiel."
                +"<h2>Wie das Spiel in dem Programm funktioniert:</h2>"+ "<br/>"
                +"Weiß beginnt. Wenn man einen Stein bewegen möchte, so drückt man auf ihn. Sind Züge mit dem angeklickten Stein möglich, so werden sie angezeigt. Drückt man auf eines der möglichen Felder, so bewegt sich der Stein auf das gewählte Feld. <br/>"
                +"Jeder hat die selbe Zeit. Die Zeit vom Spieler wird gestoppt, wenn er nicht am Zug ist und läuft dementsprechend weiter, wenn der Spieler am Zug ist. Läuft die Zeit auf 0, so hat man verloren."
                +"<h2>Wie das Spiel Dame generell funktioniert:</h2>"+ "<br/>"
                +"Jeder Spieler beginnt mit 12 Steinen, die in der Regel in den drei Reihen vor ihm platziert werden. Die Steine bewegen sich diagonal über das Brett. <br/>"
                +"Der Spieler, der die weißen Steine hat, beginnt das Spiel. Die Spieler wechseln sich dann ab, um ihre Steine zu bewegen.<br/>"
                +"Ein Stein kann nur ein Feld diagonal vorwärts bewegt werden, es sei denn, er schlägt einen gegnerischen Stein. In diesem Fall muss der gegnerische Stein übersprungen und auf das dahinterliegende Feld gesetzt werden. Der geschlagene Stein wird dann vom Brett genommen.<br/>"
                +"Falls man einen Stein schlägt, so ist man nochmal dran.<br/>"
                +"Wenn ein Stein die gegnerische Grundlinie erreicht, wird er zur Dame befördert. Eine Dame kann sowohl diagonal vorwärts als auch rückwärts bewegt werden und kann beliebig viele Felder überspringen, um Steine zu schlagen.<br/>"
                +"Das Spiel endet, wenn ein Spieler keine Steine mehr hat, beziehungsweise, wenn die Zeit abgelaufen ist.<br/>"
                +"<br/>"
                + "</html>");

        add(label);




        //pack();
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setVisible (true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new TutorialMVC("Tutorial");
    }

}
