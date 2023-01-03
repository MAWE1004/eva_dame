package socket_test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 1234;
        DatagramSocket sock = null;
        sock = new DatagramSocket(port);
        System.out.println("Server hört auf UDP");
        System.out.println(sock.getLocalPort());
        System.out.println(" mit IP-Adresse: " + sock.getLocalAddress().toString());

        byte[] buffer = new byte[50];
        DatagramPacket query = new DatagramPacket(buffer, buffer.length);
        sock.receive(query);
        System.out.println("UDP-Anfrage von ");
        System.out.println(query.getAddress().toString() + ": ");
        System.out.println(query.getPort());
        System.out.println("DATEN :");
        for (byte data :query.getData()) {
                System.out.print((char) data);
        }
        DatagramPacket answer = new DatagramPacket(buffer, buffer.length, query.getAddress(), query.getPort());
        sock.send(answer);
        sock.close();
    }
}
