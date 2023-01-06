package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
    public static void main(String[] args) throws Exception{
        int port = 1235;
        InetAddress group = InetAddress.getByName ("225.0.0.1");
        MulticastSocket sock = new MulticastSocket (port) ;
        sock.joinGroup (group); ;
        byte [ ] buffer = new byte [10000] ;
        byte [ ] buffer2 = "Hallo".getBytes();
        DatagramPacket messageIn = new DatagramPacket (buffer, buffer.length);
        DatagramPacket messageOut = new DatagramPacket (buffer2, buffer2.length, group, port) ;
        for (int i = 0; i < 10; i++) {
            sock.send(messageOut);
            sock.receive (messageIn) ;
            System.out.println ("Received:" + new String (messageIn.getData () ));
            Thread.sleep(2000);
        }
        sock.leaveGroup (group) ;
        sock.close ();
    }
}
