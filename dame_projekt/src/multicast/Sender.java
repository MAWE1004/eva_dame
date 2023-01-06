package multicast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

    public static void main(String[] args) throws Exception{
        int port = 6789;
        InetAddress group1 = InetAddress.getByName ("225.0.0.1");
        InetAddress group2 = InetAddress.getByName ("225.0.0.2");
        DatagramSocket sock = new DatagramSocket ( ) ;
        int value = (int) (int) (Math.random () * 1000);;
        String svalue1 = String.valueOf (value) + "Gruppe 1";
        String svalue2 = String.valueOf (value) + "Gruppe 2";
        byte [] message = svalue1.getBytes ();
        DatagramPacket messageOut1 = new DatagramPacket (message, message.length, group1,port);
        message = svalue2.getBytes ();
        DatagramPacket messageOut2 = new DatagramPacket (message, message.length, group2,port);
        while(true){
            sock.send (messageOut1);
            sock.send (messageOut2);

        }

//        sock.close ();
    }
}
