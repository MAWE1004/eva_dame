package socket;

import java.io.File;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ServerDame {
    private final int MAX_PACKET = 100;
    private int port;
    private DatagramSocket sock = null;
    private int gameCount;
    private ArrayList<String> players = null;
    private Map<String,String> pairs = null;

    public ServerDame (/*DatagramSocket socket*/ int port) throws Exception{
        this.port = port;
        sock = new DatagramSocket(port)/*socket*/;
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
            case "zug" :
                buffer = serviceZug(buffer);
                code = "ok";
                break;
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
                    ResponseForPlayer response = new ResponseForPlayer("ok", "10", player, "s");
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

        ResponseForPlayer response = new ResponseForPlayer("ok", "10", player, "w");
        return response.marshall();
    }

    public byte[] serviceZug(byte[] buffer) throws Exception {
//        byte[] buffer = new byte[6];
//        DatagramPacket query = new DatagramPacket(buffer, buffer.length);
//        sock.receive(query);
//        System.out.println("UDP-Anfrage von ");
//        System.out.println(query.getAddress().toString() + ": ");
//        System.out.println(query.getPort());
//        buffer = query.getData();
        RequestForZug request = new RequestForZug().unMarshall(buffer);
        System.out.println("Angekommen: " + request.toString());

//        DatagramPacket answer = new DatagramPacket(buffer, buffer.length);
        ResponseForZug response = new ResponseForZug("ok");
        return response.marshall();
//        answer.setData(buffer);
//        answer.setAddress(query.getAddress());
//        answer.setPort(query.getPort());
//        sock.send(answer);
    }

}