package socket;

import controller.menu.MenuController;
import models.Menu;

public class ServerAliveThread extends Thread{

    private final static long UPDATE_INTERVAL = 10;
    private Menu model;
    private MenuController controller;

    public ServerAliveThread(Menu model, MenuController controller){
        this.model = model;
        this.controller = controller;
        start();
    }

    public void run(){
        try {
            while(!isInterrupted()) {
                System.out.println("===== Asking Server if alive =====");
                if (!model.serverAlive()) {
                    interrupt();
                    throw new Exception("Server not alive anymore");
                }
                System.out.println("===== Server still alive =====");
                Thread.sleep(UPDATE_INTERVAL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            controller.endAll();
            System.out.println("===== Server beendet =====");
        }
    }

}
