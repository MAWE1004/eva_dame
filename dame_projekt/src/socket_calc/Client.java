package socket_calc;

import java.io.IOException;
import java.net.*;

public class Client {
    private InetAddress address;
    private int port;
    private DatagramSocket sock = null;
    private SocketAddress sockAdr = null;

    Client(InetAddress adr, int port) throws Exception{
        address = adr;
        this.port = port;
        sock = new DatagramSocket();
        sock.setSoTimeout(1000);
        sockAdr = new InetSocketAddress(address, port);
    }

    public void request(double a, double b, String op) throws IOException {
        Request request = new Request (a, b, op);
        byte[] buffer = request.marshall();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        packet.setSocketAddress(sockAdr);
        int retryCount = 4;
        while (retryCount > 0){
            try {
                sock.send(packet);
                sock.receive(packet);
                break;
            } catch(SocketTimeoutException e){
                System.err.println("Keine Antwort Server " + e);
                retryCount--;
            }
        }
        buffer = packet.getData();
        Response response = new Response().unMarshall(buffer);
        if(response.getCode().equals("ok")) {
            System.out.println("Das Ergebnis lautet: " + response.getResult());
            System.out.println(packet.getPort());
        }
        else
            System.out.println("Kein sinnvoller Auftrag");
        sock.close();
    }
}
