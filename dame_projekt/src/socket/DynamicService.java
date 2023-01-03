package socket;

import java.net.DatagramSocket;

public class DynamicService extends Thread{
    DatagramSocket connection = null;

    public DynamicService (DatagramSocket socket){
        connection = socket;
    }

    public void run(){
        try{
            connection.setSoTimeout(0);
//            ServerDame server = new ServerDame(connection);
//            server.service();
            connection.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
