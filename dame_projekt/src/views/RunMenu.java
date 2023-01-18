package views;

import controller.anmeldung.AnmeldungMVC;
import controller.menu.MenuMVC;
import models.Anmeldung;
import models.Menu;
import socket.ServerAliveThread;

public class RunMenu extends Thread{
    private Menu menu;
    private MenuMVC mvc;

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
        ServerAliveThread aliveThread = new ServerAliveThread(menu, mvc.getMenuController());
        while(!isInterrupted()){
        }
        aliveThread.interrupt();
        mvc.dispose();
    }

    public void disoseMenu(){
        mvc.dispose();
    }
}
