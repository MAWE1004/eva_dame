package socket;

import java.net.InetAddress;

public class ClientDameMain {
    public static void main(String[] args) throws Exception{
        String name = "Player 7";
        InetAddress adr = InetAddress.getByName("127.0.0.1");
        int port = 1234;
        ClientDame client = new ClientDame(adr, port);
        client.requestPlayer(name, 10);
        //client.sendPositions(new byte[]{4,4},new byte[]{7,7},new byte[]{5,5});
    }
}
