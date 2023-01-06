package socket;

import java.io.File;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ServerDame {
    private final int MAX_PACKET = 100;
    private int port;
    private MulticastSocket sock = null;
    private int[] lastMultiAdr;
    private int gameCount;
    private ArrayList<String> players = null;
    private Map<String,String> pairs = null;

    public ServerDame (/*DatagramSocket socket*/ int port) throws Exception{
        this.port = port;
        sock = new MulticastSocket(port)/*socket*/;
        lastMultiAdr = new int[]{225,0,0,0};
        gameCount = 1;
        players = new ArrayList<String>();
        pairs = new HashMap<String,String>();
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
//            case "zug" :
//                buffer = serviceZug(buffer);
//                code = "ok";
//                break;
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
            default:
                code = "bad";
        }

        System.out.println("GROESSE BUFFER: " + buffer.length);

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
                //System.out.println(name);
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

        boolean gameExists = false;

        String line;
        FileWriter myWriter = new FileWriter("search.txt", true);
        Scanner read = new Scanner(new File("search.txt"));
        while (read.hasNextLine()){
            line = read.nextLine();
            if(line.equals(String.valueOf(time))){
                line = read.nextLine();
                line = read.nextLine();
                System.out.println("Letzte Line mit s: " + line);
                if(line.equals("s:")){
                    System.out.println("if(line.equals(\"s:\"))");
                    myWriter.write(player + '\n');
                    myWriter.close();
                    String multiAdr = generateMultiAdr();
                    ResponseForPlayer response = new ResponseForPlayer("ok", multiAdr, "10", player, "s");
                    return response.marshall();
                    //gameExists = true;
                }
            }
        }
        myWriter.write("g"+gameCount + '\n');
        gameCount++;
        myWriter.write(String.valueOf(time) + '\n');
        myWriter.write("w:" + player + '\n');
        myWriter.write("s:");
        myWriter.close();

        String multiAdr = generateMultiAdr();

        ResponseForPlayer response = new ResponseForPlayer("ok", multiAdr, "10", player, "w");
        return response.marshall();
    }

    public byte[] serviceZug(byte[] buffer) throws Exception {
        RequestForZug request = new RequestForZug().unMarshall(buffer);
        System.out.println("Angekommen: " + request.toString());

        int port = 1234;
        InetAddress group = InetAddress.getByName("225.0.0.1");

        byte[] message = "Hallo".getBytes();

        DatagramPacket messageOut = new DatagramPacket(message, message.length, group, port);
        sock.send(messageOut);


        ResponseForZug response = new ResponseForZug("ok");
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