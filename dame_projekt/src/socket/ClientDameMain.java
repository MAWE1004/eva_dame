package socket;

import java.net.InetAddress;

public class ClientDameMain {
    public static void main(String[] args) throws Exception{
        String name = "Player 4";
        InetAddress adr = InetAddress.getByName("10.0.3.36");
        int port = 1234;
        ClientDame client = new ClientDame(adr, port);
        client.requestPlayer(name);
    }
}
