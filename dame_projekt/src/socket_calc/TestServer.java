package socket_calc;

public class TestServer {
    public static void main(String[] args) throws Exception {
        int port = 1236;
        Server server = new Server(port);
        while(true){
            server.service();
            System.out.println("Warte");
        }
    }
}
