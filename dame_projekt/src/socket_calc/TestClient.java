package socket_calc;

import java.net.InetAddress;

public class TestClient {
    public static void usage(){
        System.out.println("usage");
        System.out.println("\t [Operand] [Operator <add|sub|mul|div>] [Operand]");
    }

    public static void main(String[] args) throws Exception{
        if (args.length < 2 || args.length >3) {
            usage();
            System.exit(1);
        }
        double a = Integer.parseInt (args [0]) ;
        String op = args [1] ;
        double b = Integer.parseInt (args [2]) ;
        InetAddress address = InetAddress.getByName("10.0.3.36");
        int port = 1236;
        Client client = new Client(address, port);
        client.request(a,b,op);
    }
}
