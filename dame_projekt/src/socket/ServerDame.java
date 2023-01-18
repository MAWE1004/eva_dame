package socket;

import models.SearchGamer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class ServerDame {
    private final int MAX_PACKET = 100;
    private int port;
    private DatagramSocket sock = null;
    private MulticastSocket multicastSocket = null;
    private int[] lastMultiAdr;
    private int gameCount;
    private ArrayList<String> players = null;
    private Map<String,String> pairs = null;
    private List<SearchGamer> supplierNames = new ArrayList<>();

    public ServerDame (int port) throws Exception{
        this.port = port;
        sock = new DatagramSocket(port);
        multicastSocket = new MulticastSocket(port+1);
        lastMultiAdr = new int[]{225,0,0,0};
        gameCount = 1;
        players = new ArrayList<String>();
        pairs = new HashMap<String,String>();

    }

    public DatagramSocket getSocket() {
        return sock;
    }

    public void service() throws Exception{
        byte[] buffer = new byte[MAX_PACKET];
        DatagramPacket query = new DatagramPacket(buffer, buffer.length);
        sock.receive(query);
        System.out.println("UDP-Anfrage von ");
        System.out.println(query.getAddress().toString() + ": ");
        System.out.println(query.getPort());

        buffer = query.getData();
        String code;
        code = new String(buffer).substring(0,3);
        System.out.println("CODE: " +  code);
        buffer = Arrays.copyOfRange(buffer, 3, buffer.length);
        System.out.println("COPY: " + new String(buffer));

        switch(code){
            case "spi" :
                buffer = serviceSearch(buffer);
                code = "ok";
                break;
            case "anm" :
                buffer = serviceAnmeldung(buffer);
                code = "ok";
                break;
            case "out" :
                code = "ok";
                break;
            case "reg" :
                buffer = serviceRegistrierung(buffer);
                code = "ok";
                break;
            case "ok":
                System.out.println("HIER");
                code = "ok";
            default:
                code = "bad";
        }


        //sending Response
        DatagramPacket answer = new DatagramPacket(buffer, buffer.length);
        answer.setData(buffer);
        answer.setAddress(query.getAddress());
        answer.setPort(query.getPort());
        sock.send(answer);

    }

    public void serviceAlive() throws IOException {
        byte[] buffer = new byte[3];
        DatagramPacket query = new DatagramPacket(buffer, buffer.length);
        sock.receive(query);
        System.out.println("UDP-Anfrage von ");
        System.out.println(query.getAddress().toString() + ": ");
        System.out.println(query.getPort());

        //sending Response
        DatagramPacket answer = new DatagramPacket(buffer, buffer.length);
        answer.setData(buffer);
        answer.setAddress(query.getAddress());
        answer.setPort(query.getPort());
        sock.send(answer);
    }

    private byte[] serviceRegistrierung(byte[] buffer) throws Exception{
        RequestForRegistrierung request = new RequestForRegistrierung().unMarshall(buffer);
        String requestName = request.getName();
        String requestPassword = request.getPassword();

        boolean nameExists = false;

        String line;
        Scanner read = new Scanner(new File("/home/student10/anmeldung.txt"));
        while (read.hasNextLine()){
            line = read.nextLine();
            if(line.charAt(0) == 'u') {
                line = line.substring(3);
                System.out.println(line);
                if (line.toLowerCase().equals(requestName.toLowerCase())) {
                    System.out.println("Username schon vorhanden!");
                    nameExists = true;
                    break;
                }
            }
        }

        if(!nameExists){
            FileWriter myWriter = new FileWriter("/home/student10/anmeldung.txt", true);
            myWriter.write("u: " + requestName + '\n');
            myWriter.write("p: " + requestPassword + '\n');
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            ResponseForRegistrierung response = new ResponseForRegistrierung("ok");
            return response.marshall();
        }
        ResponseForRegistrierung response = new ResponseForRegistrierung("bad");
        return response.marshall();
    }

    private byte[] serviceAnmeldung(byte[] buffer) throws Exception{
        RequestForAnmeldung request = new RequestForAnmeldung().unMarshall(buffer);
        String requestName = request.getName();
        String requestPassword = request.getPassword();

        String line;
        String name;
        String password;
        Scanner read = new Scanner(new File("/home/student10/anmeldung.txt"));
        while (read.hasNextLine()){
            line = read.nextLine();
            if(line.charAt(0) == 'u') {
                name = line.substring(3);
                if (name.toLowerCase().equals(name.toLowerCase())) {
                    System.out.println(name);
                    line = read.nextLine();
                    password = line.substring(3);
                    System.out.println(password);
                    if(password.equals(requestPassword.toLowerCase())){
                        System.out.println("Username und Passwort stimmen Ã¼berein");
                        ResponseForAnmeldung response = new ResponseForAnmeldung("ok");
                        return response.marshall();
                    }
                }
            }
        }
        System.out.println("Username oder Passwort falsch!");
        ResponseForAnmeldung response = new ResponseForAnmeldung("bad");
        return response.marshall();
    }

    public byte[] serviceSearch(byte[] buffer) throws Exception{
        RequestForPlayer request = new RequestForPlayer().unMarshall(buffer);
        String player = request.getSpieler();
        byte time = request.getTime();

        System.out.println("Time (SERVER): " + time);
        System.out.println("Spieler (Server): " + player);

        SearchGamer searchGamer;

        for(int i = 0; i < supplierNames.size(); i++){
            if(!supplierNames.get(i).getGamerWhite().equals(player) && supplierNames.get(i).getGamerBlack() == null && supplierNames.get(i).getTime() == time){

                supplierNames.get(i).setGamerBlack(player);
                System.out.println(supplierNames.toString());

                try {
                    System.out.println("MULTI ADR von " + supplierNames.get(i).getGamerBlack() + "with adr " + supplierNames.get(i).getMultiAdr());
                    InetAddress gruppe = InetAddress.getByName(supplierNames.get(i).getMultiAdr());
                    int port = 1235;
                    MulticastSocket socket = new MulticastSocket(port);
                    socket.joinGroup(gruppe);

                    SendGegner send = new SendGegner(player);
                    byte[] bufferMulti = send.marshall();
                    DatagramPacket packet = new DatagramPacket(bufferMulti, bufferMulti.length, gruppe, port);
                    socket.setTimeToLive(5);
                    socket.send(packet);

                    DatagramPacket receive = new DatagramPacket(bufferMulti, bufferMulti.length);
                    socket.receive(receive);
                    bufferMulti = receive.getData();
                    SendGegner response = new SendGegner().unMarshall(bufferMulti);
                    System.out.println("Received: " + response.getGegner());
                    socket.leaveGroup(gruppe);
                    socket.close();

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ResponseForPlayer response = new ResponseForPlayer("ok", supplierNames.get(i).getMultiAdr(), supplierNames.get(i).getGame(), supplierNames.get(i).getGamerWhite(), "s");
                return response.marshall();
            }
            else {
                continue;
            }
        }
        searchGamer = new SearchGamer();
        String multiAdr = generateMultiAdr();
        searchGamer.setGame("g" + Integer.toString(gameCount++));
        searchGamer.setGamerWhite(player);
        searchGamer.setTime(time);
        searchGamer.setMultiAdr(multiAdr);
        supplierNames.add(searchGamer);

        System.out.println("White Player: " + searchGamer);

        ResponseForPlayer response = new ResponseForPlayer("ok",multiAdr, searchGamer.getGame() , " ", "w");
        return response.marshall();
    }

    private String generateMultiAdr(){
        if(lastMultiAdr[3] == 255) {
            lastMultiAdr[3] = 0;
            if (lastMultiAdr[2] == 255) {
                lastMultiAdr[2] = 0;
                if (lastMultiAdr[1] == 255) {
                    lastMultiAdr[1] = 0;
                    if (lastMultiAdr[0] == 239) {
                        lastMultiAdr[0] = 225;
                    } else
                        lastMultiAdr[0]++;
                }
                else
                    lastMultiAdr[1]++;
            }else
                lastMultiAdr[2]++;
        }
        else
            lastMultiAdr[3]++;
        return new String(lastMultiAdr[0] + "." + lastMultiAdr[1] + "." + lastMultiAdr[2] + "." + lastMultiAdr[3]);
    }

}