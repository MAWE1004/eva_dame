package models.thread;

import controller.menu.MenuController;
import models.Menu;

public class ServerAliveThread extends Thread{

    private final static long UPDATE_INTERVAL = 20000;
    private Menu model;
    private MenuController controller;
    private RunMenu runMenu;

    public ServerAliveThread(Menu model, MenuController controller, RunMenu runMenu){
        this.model = model;
        this.controller = controller;
        this.runMenu = runMenu;
        start();
    }

    public void run(){
        try {
            int count = 1;
            while(!isInterrupted()) {
                System.out.println("===== Asking Server if alive =====" + count++);
                if (!model.serverAlive()) {
                    throw new Exception("Server not alive anymore");
                }
                System.out.println("===== Server still alive =====");
                Thread.sleep(UPDATE_INTERVAL);
            }
        } catch (InterruptedException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("===== Server beendet =====");
            controller.endAll();
        }
    }
}
