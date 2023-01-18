package socket;

import java.nio.ByteBuffer;

public class SendAlive {
    String code;

    public SendAlive(){
        code = "ok";
    }

    public String getCode() {
        return code;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(3);
        out.put(code.getBytes());
        buffer = out.array();
        return buffer;
    }

    public SendAlive unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        byte[] sin = new byte[3];
        in.get(sin, 0, 3);
        code = new String(sin);
        return this;
    }
}
