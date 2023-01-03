package socket_test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void usage() {
        System.out.println("usage");
        System.out.println("\t Client [< connect IP >] [< connect port >]");
    }

    public static void main(String[] args) throws Exception{
        int port = 1234;
        InetAddress address = null;
        if(args.length > 2){
            usage();
            System.exit(1);
        }

        if(args.length > 0 )
            address = InetAddress.getByName(args[0]);
        else
            address = InetAddress.getByName("127.0.0.1");

        if(args.length > 1)
            port = Integer.parseInt(args[1]);
        if(port <= 0 || port > 65535) {
            System.out.println(" Port liegt nicht im gültigen Bereich!");
            System.exit(1);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte Text eingeben zum senden");
        byte[] buffer = sc.nextLine().getBytes();
        DatagramSocket sock = new DatagramSocket();
//        byte[] buffer = "HELLO WORLD".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        sock.send(packet);
        System.out.println("Client hat Request versendet! ");
        System.out.println(packet);
        System.out.println(packet.getAddress().toString());
        System.out.println(packet.getPort());

        sock.close();
    }
}
