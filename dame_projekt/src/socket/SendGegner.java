package socket;

import java.nio.ByteBuffer;

public class SendGegner {
    String gegner;

    public SendGegner(){
    }

    public SendGegner(String gegner) {
        this.gegner = gegner;
    }

    public String getGegner() {
        return gegner;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(11);
        out.put((byte) gegner.length());
        out.put(gegner.getBytes());
        buffer = out.array();
        return buffer;
    }

    public SendGegner unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        int len = in.get();
        byte[] sin = new byte[len];
        in.get(sin, 0, len);
        gegner = new String(sin);

        return this;
    }
}
