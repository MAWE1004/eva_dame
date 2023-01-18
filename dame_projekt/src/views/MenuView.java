package views;

import controller.GameAndMenuMVC;
import controller.menu.MenuController;
import controller.menu.MenuMVC;
import models.Menu;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel implements MenuListener{
    private Menu model;
    private MenuMVC menuMVC;

    public MenuView(Menu model, MenuMVC menuMVC){
        super();
        this.model = model;
        this.menuMVC = menuMVC;
        nameChanged(model);
        view();
    }

    private void view(){
        setLayout(new GridLayout(0,1));
        setPreferredSize(new Dimension(350, 350));

        JButton tutorial = new JButton("Tutorial");
        JButton playFive = new JButton("Play 5 Min");
        JButton playTen = new JButton("Play 10 Min");
        JButton playThirty = new JButton("Play 30 Min");
        JButton logout = new JButton("Logout");

        MenuController menuController = new MenuController(null, menuMVC);

        tutorial.addActionListener(menuController);
        playFive.addActionListener(menuController);
        playTen.addActionListener(menuController);
        playThirty.addActionListener(menuController);
        logout.addActionListener(menuController);

        add(tutorial);
        add(playFive);
        add(playTen);
        add(playThirty);
        add(logout);
    }


    @Override
    public void nameChanged(Menu model) {
//        setText(model.getName());
    }
}
