package socket;

import java.io.IOException;
import java.net.*;

public class ClientDame {
    private static final int RETRYCOUNT = 4;

    private InetAddress address;
    private int port;
    private DatagramSocket socket = null;
    private SocketAddress socketAdr = null;

    public ClientDame(InetAddress adr, int port) throws Exception{
        address = adr;
        this.port = port;
        socket = new DatagramSocket();
        socket.setSoTimeout(2000);
        socketAdr = new InetSocketAddress(address, port);
    }

    public boolean requestAnmeldung(String name, String password) throws IOException{
        RequestForAnmeldung request = new RequestForAnmeldung(name, password);
        byte[] buffer = request.marshall();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        packet.setSocketAddress(socketAdr);
        int retryCount = RETRYCOUNT;
        while (retryCount > 0) {
            try {
                socket.send(packet);
                socket.receive(packet);
                break;
            } catch (SocketTimeoutException e){
                System.err.println("Keine Antwort Server " + e);
                retryCount--;
            }
        }
        buffer = packet.getData();
        ResponseForAnmeldung response = new ResponseForAnmeldung().unMarshall(buffer);
        if(response.getCode().equals("ok")){
            System.out.println("Anmeldung erfolgreich");
            socket.close();
            return true;
        }
        System.out.println("Anmeldung fehlgeschlagen");
        socket.close();
        return false;
    }

    public boolean requestRegistrierung(String name, String password) throws  IOException{
        RequestForRegistrierung request = new RequestForRegistrierung(name, password);
        byte[] buffer = request.marshall();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        packet.setSocketAddress(socketAdr);
        int retryCount = RETRYCOUNT;
        while (retryCount > 0) {
            try {
                socket.send(packet);
                socket.receive(packet);
                break;
            } catch (SocketTimeoutException e){
                System.err.println("Keine Antwort Server " + e);
                retryCount--;
            }
        }buffer = packet.getData();
        ResponseForRegistrierung response = new ResponseForRegistrierung().unMarshall(buffer);
        if(response.getCode().equals("ok")){
            System.out.println("Registrierung erfolgreich");
            socket.close();
            return true;
        }
        System.out.println("Registrierung fehlgeschlagen");
        socket.close();
        return false;

    }

    public void requestPlayer(String sp) throws IOException {
        RequestForPlayer request = new RequestForPlayer(sp);
        byte[] buffer = request.marshall();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        packet.setSocketAddress(socketAdr);
        int retryCount = RETRYCOUNT;
        while (retryCount > 0) {
            try {
                socket.send(packet);
                socket.receive(packet);
                break;
            } catch (SocketTimeoutException e){
                System.err.println("Keine Antwort Server " + e);
                retryCount--;
            }
        }
        buffer = packet.getData();
        ResponseForPlayer response = new ResponseForPlayer().unMarshall(buffer);
        if(response.getCode().equals("ok"))
            System.out.println("Der Gegner ist: " + response.getSpieler());
        else
            System.out.println("Es wurde kein Gegner gefunden");
        socket.close();
    }

    public void sendPositions(byte[] old, byte[] neu, byte[] schlagen) throws IOException{
        RequestForZug request = new RequestForZug(old[0], old[1], neu[0], neu[1], schlagen[0], schlagen[1]);
        byte[] buffer = request.marshall();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        packet.setSocketAddress(socketAdr);
        int retryCount = RETRYCOUNT;
        while (retryCount > 0) {
            try {
                socket.send(packet);
                socket.receive(packet);
                break;
            } catch (SocketTimeoutException e){
                System.err.println("Keine Antwort Server " + e);
                retryCount--;
            }
        }
        buffer = packet.getData();
        ResponseForZug response = new ResponseForZug().unMarshall(buffer);
        if(response.getCode().equals("ok"))
            System.out.println("Alles angekommen");
        else
            System.out.println("Kein sinnvoller Auftrag");
        socket.close();
    }
}
