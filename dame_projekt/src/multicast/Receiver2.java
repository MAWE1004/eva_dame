package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver2 {
    public static void main(String[] args) throws Exception{
        int port = 6789;
        InetAddress group = InetAddress.getByName ("225.0.0.2");
        MulticastSocket sock = new MulticastSocket (port) ;
        sock.joinGroup (group); ;
        byte [ ] buffer = new byte [10000] ;
        DatagramPacket messageIn = new DatagramPacket (buffer, buffer.length) ;
        for (int i = 0; i < 10; i++) {
            sock.receive (messageIn) ;
            System.out.println ("Received:" + new String (messageIn.getData () ));
            Thread.sleep(2000);
        }
        sock.leaveGroup (group) ;
        sock.close ();
    }
}
