package socket;

import java.net.DatagramSocket;

public class ServerDameMain {
    public static void main(String[] args) throws Exception{
        int port = 1234;
        ServerDame server = new ServerDame(port);
        
        while(true) {
            server.service();
        }

    }
}
