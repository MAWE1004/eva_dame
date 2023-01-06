package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

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

    public boolean sendPos(String farbe, byte[] old, byte[] neu, byte[] schlagen) throws IOException {
        SendZug request = new SendZug(farbe, old[0], old[1], neu[0], neu[1], schlagen[0], schlagen[1]);
        byte[] buffer = request.marshall();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
        multicastSocket.send(packet);
        int retryCount = RETRYCOUNT;

        return true;
    }

    public boolean receivePos() throws IOException {
        byte[] buffer = new byte[100];
        DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
        multicastSocket.receive(receive);
        buffer = receive.getData();
        SendZug response = new SendZug().unMarshall(buffer);
        System.out.println("Received: " + response.toString());

        return true;
    }

}
