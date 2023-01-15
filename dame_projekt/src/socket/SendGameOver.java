package socket;

import java.nio.ByteBuffer;

public class SendGameOver {
    private byte code;

    public SendGameOver(){
    }

    public SendGameOver(byte code){
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(5);
        String value = "time";
        out.put(value.getBytes());
        out.put(code);
        buffer = out.array();
        return buffer;
    }

    public SendGameOver unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        byte[] sin = new byte[4];
        in.get(sin, 0, 4);
        if(!"time".equals(new String(sin)))
            return null;

        code = in.get();
        return this;
    }
}
