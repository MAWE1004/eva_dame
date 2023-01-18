package views;

import controller.menu.MenuMVC;
import models.Menu;
import socket.ServerAliveThread;

public class RunMenu extends Thread{
    private Menu menu;
    private MenuMVC mvc;
    private ServerAliveThread aliveThread;

    public RunMenu(Menu menu) {
        this.menu = menu;
        start();
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuMVC getMvc() {
        return mvc;
    }

    @Override
    public void run(){
        mvc = new MenuMVC(this, "Menu");
        aliveThread = new ServerAliveThread(menu, mvc.getMenuController(), this);
    }

    public void closeMenu(){
        aliveThread.interrupt();
        mvc.dispose();
        interrupt();
    }

}
