package socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ClientMultiDameMain {
    public static void main(String[] args) throws IOException {
        ClientMultiDame sock = new ClientMultiDame(InetAddress.getByName("225.0.0.1"), 1235);
        sock.sendPos("w",new byte[]{1,2}, new byte[]{1,3},new byte[]{1,4});
        sock.receivePos();
        sock.receivePos();
    }
}
