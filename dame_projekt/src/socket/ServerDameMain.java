package socket;

import java.net.DatagramSocket;

public class ServerDameMain {
    public static void main(String[] args) throws Exception{
        int port = 1234;
        int port = 6789;
//        DatagramSocket socket = new DatagramSocket(port);
        ServerDame server = new ServerDame(port);
//        socket.setSoTimeout(0);

        DynamicService service = null;
        while(true) {
            server.service();
//            service = new DynamicService(socket);
//            service.start();
        }

    }
}
