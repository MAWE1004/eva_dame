package socket;

import controller.menu.MenuController;
import models.Menu;

public class ServerAliveThread extends Thread{

    private final static long UPDATE_INTERVAL = 20000;
    private Menu model;
    private MenuController controller;

    public ServerAliveThread(Menu model, MenuController controller){
        this.model = model;
        this.controller = controller;
        start();
    }

    public void run(){
        try {
            int count = 1;
            while(!isInterrupted()) {
                System.out.println("===== Asking Server if alive =====" + count++);
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

    public static void main(String[] args) {
        Menu model = new Menu(null, null);
        MenuController controller = new MenuController(null, null);
        new ServerAliveThread(model, controller);
    }

}
