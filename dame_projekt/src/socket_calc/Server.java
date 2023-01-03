package socket_calc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    private final int MAX_PACKET = 100;
    private int port;
    private DatagramSocket sock = null;

    public Server (int port) throws Exception{
        this.port = port;
        sock = new DatagramSocket(port);
    }

    public void service() throws Exception{
        byte[] buffer = new byte[MAX_PACKET];
        DatagramPacket query = new DatagramPacket(buffer, buffer.length);
        sock.receive(query);
        System.out.println("UDP-Anfrage von ");
        System.out.println(query.getAddress().toString() + ": ");
        System.out.println(query.getPort());
        buffer = query.getData();
        Request request = new Request().unMarshall(buffer);
        // supporting request
        double result = 0;
        String code;
        switch (request.getOp()){
            case "add":
                result = request.getA() + request.getB();
                code = "ok";
                break;
            case "sub":
                result = request.getA() - request.getB();
                code = "ok";
                break;
            case "mul":
                result = request.getA() * request.getB();
                code = "ok";
                break;
            case "div":
                result = request.getA() / request.getB();
                code = "ok";
                break;
            default:
                code = "bad";
                break;
        }

        //sending response
        DatagramPacket answer = new DatagramPacket(buffer, buffer.length);
        Response response = new Response(result, code);
        buffer = response.marshall();
        answer.setData(buffer);
        answer.setAddress(query.getAddress());
        answer.setPort(query.getPort());
        sock.send(answer);
    }

}

