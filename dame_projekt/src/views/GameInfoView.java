package views;

import controller.GameClockController;
import models.GameClock;
import models.GameInfo;

import javax.swing.*;
import java.awt.*;

public class GameInfoView extends JPanel implements GameInfoListener{
    private JLabel user;
    private JLabel userStein;
    private JLabel gegner;
    private JLabel gegnerStein;

    public GameInfoView(GameInfo model){
        super();
        setLayout(new GridLayout(0,1));

        JPanel time = new JPanel();
        JLabel labelTime = new JLabel ("", SwingConstants.RIGHT);
        labelTime.setFont(new Font(Font.DIALOG,Font.BOLD, 40));
        System.out.println("TIME: " + model.getTime());
        GameClock clock = new GameClock (labelTime, model.getTime());
        GameClockController gameClockController = new GameClockController (clock);
        time.add(labelTime);

        JPanel turn = new JPanel();
        turn.setLayout(new GridLayout(4,0));
        user = new JLabel("User: " + model.getUser());
        userStein = new JLabel("Farbe: " + model.getUserStein());
        gegner = new JLabel("Gegner: " + model.getGegner());
        gegnerStein = new JLabel("Farbe: " + model.getGegnerStein());

        user.setFont(new Font(Font.SERIF,Font.BOLD, 30));
        gegner.setFont(new Font(Font.SERIF,Font.BOLD, 30));
        userStein.setFont(new Font(Font.SERIF,Font.BOLD, 30));
        gegnerStein.setFont(new Font(Font.SERIF,Font.BOLD, 30));

        turn.add(user, BorderLayout.CENTER);
        turn.add(userStein, BorderLayout.CENTER);
        turn.add(gegner, BorderLayout.CENTER);
        turn.add(gegnerStein, BorderLayout.CENTER);

        add(time);
        add(turn);
    }

    @Override
    public void valueChanged(GameInfo model) {
        user = new JLabel("User: " + model.getUser());
        userStein = new JLabel("Farbe: " + model.getUserStein());
        gegner = new JLabel("Gegner: " + model.getGegner());
        gegnerStein = new JLabel("Farbe: " + model.getGegnerStein());

        validate();
        repaint();
    }
}
