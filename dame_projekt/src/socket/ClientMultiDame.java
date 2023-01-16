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

    public SendZug receivePos() throws IOException {
        byte[] buffer = new byte[100];
        DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
        multicastSocket.receive(receive);
        buffer = receive.getData();
        try {
            if (new SendGameOver().unMarshall(buffer).getKey().equals("time")) {
                receiveGameOver(buffer);
                return null;
            }
        } catch (NullPointerException e){

        }
        SendZug response = new SendZug().unMarshall(buffer);
        System.out.println("Received: " + response.toString());

        return response;
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
        switch (value){
            case 0:
                //zeit abgelaufen
                new ErgebnisMVC("Spiel ist vorbei, die Zeit ist abgelaufen! Du hast verloren.");
                break;
            case 1:
                //alle gegnerischen steine geschlagen
                new ErgebnisMVC("Spiel ist vorbei, alle Steine sind geschlagen! Du hast gewonnen.");
                break;
            case 2:
                break;
            default:
                //spiel ist beendet, weil gegner geschlossen hat -> sendGameOver
                new ErgebnisMVC("Spiel ist vorbei, du hast das Spiel abgebrochen! Du hast verloren.");
        }
    }

    public void receiveGameOver(byte[] buffer) throws IOException {
//        byte[] buffer = new byte[100];
//        DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
//        multicastSocket.receive(receive);
//        buffer = receive.getData();
        SendGameOver response = new SendGameOver().unMarshall(buffer);
        System.out.println("CODE " + response.getCode());
        switch (response.getCode()){
            case 0:
                //zeit abgelaufen
                new ErgebnisMVC("Spiel ist vorbei, die Zeit deines Gegners ist abgelaufen! Du hast gewonnen.");
                break;
            case 1:
                //alle gegnerischen steine geschlagen
                new ErgebnisMVC("Spiel ist vorbei, all deine Steine sind geschlagen! Du hast verloren.");
                break;
            case 2:
                break;
            default:
                //spiel ist beendet, weil gegner geschlossen hat -> sendGameOver
                new ErgebnisMVC("Spiel ist vorbei, der Gegner hat das Spiel abgebrochen! Du hast gewonnen.");
        }

        System.out.println("Received: " + response.toString());
    }
}
