package socket;

import java.nio.ByteBuffer;

public class SendGameOver {
    private String key;
    private byte code;

    public SendGameOver(){
    }

    public SendGameOver(byte code){
        this.key = "time";
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(5);
        out.put(key.getBytes());
        out.put(code);
        buffer = out.array();
        return buffer;
    }

    public SendGameOver unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        byte[] sin = new byte[4];
        in.get(sin, 0, 4);
        key = new String(sin);
        if(!"time".equals(key))
            return null;

        code = in.get();
        return this;
    }
}
