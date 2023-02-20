package socket;

public class SeverDameMainTest {
    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(args[0]);
        ServerDameTest server = new ServerDameTest(port);

        while (true) {
            server.service();
        }
    }
}
