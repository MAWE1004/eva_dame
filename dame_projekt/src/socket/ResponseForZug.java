package socket;

import java.nio.ByteBuffer;

public class ResponseForZug {
    private String code;

    public ResponseForZug(){
    }

    public ResponseForZug(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public ResponseForZug unMarshall(byte[] res){
        ByteBuffer in = ByteBuffer.wrap(res);
        int len = 2;
        if(res.length == 3)
            len++;
        byte[] sin = new byte[len];
        in.get(sin, 0, len);
        code = new String(sin);
        return this;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer bbuf = ByteBuffer.allocate(3);
        bbuf.put(code.getBytes());
        buffer = bbuf.array();
        return buffer;
    }
}
