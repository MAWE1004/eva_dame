package socket;

import controller.ErgebnisMVC;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientMultiDame {
    private static final int RETRYCOUNT = 4;

    private InetAddress group;
    private int port;
    private MulticastSocket multicastSocket = null;

    public ClientMultiDame(InetAddress group, int port) throws IOException {
        this.group = group;
        this.port = port;
        multicastSocket = new MulticastSocket(port);
        this.group = group;
        multicastSocket.joinGroup(group);
    }

    public void endMultiSocket() throws IOException {
        multicastSocket.leaveGroup(group);
        multicastSocket.close();
    }

    public boolean sendOk(){



        return true;
    }

    public boolean sendPos(String farbe, byte[] old, byte[] neu, byte[] schlagen) throws IOException {
        SendZug request = new SendZug(farbe, old[0], old[1], neu[0], neu[1], schlagen[0], schlagen[1]);
        byte[] buffer = request.marshall();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
        multicastSocket.send(packet);
        int retryCount = RETRYCOUNT;

        return true;
    }

    public byte[] receivePos() throws IOException {
        byte[] buffer = new byte[100];
        DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
        multicastSocket.receive(receive);
        buffer = receive.getData();
        try {
            if (new SendGameOver().unMarshall(buffer).getKey().equals("time")) {
                receiveGameOver(buffer);
                return buffer;
            }
        } catch (NullPointerException e){
            return buffer;
        }
//        SendZug response = new SendZug().unMarshall(buffer);
//        System.out.println("Received: " + response.toString());

        return buffer;
    }

    //public sendGameOver; bytebuffer nicht größer als 5; sendzug zu sendgameover
    public void sendGameOver(byte value) throws IOException {
        SendGameOver request;
        byte[] buffer;
        DatagramPacket packet;
        request = new SendGameOver(value);
        buffer = request.marshall();
        packet = new DatagramPacket(buffer, buffer.length, group, port);
        multicastSocket.send(packet);
    }

    public int receiveGameOver(byte[] buffer) throws IOException {
        SendGameOver response = new SendGameOver().unMarshall(buffer);
        System.out.println("CODE " + response.getCode());

        return response.getCode();
    }
}
