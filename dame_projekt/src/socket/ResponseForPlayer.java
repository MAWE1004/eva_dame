package socket;

import java.nio.ByteBuffer;

public class ResponseForPlayer {
    private String spieler;
    private String code;

    public ResponseForPlayer(){
    }

    public ResponseForPlayer(String spieler, String code) {
        this.spieler = spieler;
        this.code = code;
    }

    public String getSpieler() {
        return spieler;
    }

    public String getCode() {
        return code;
    }

    public ResponseForPlayer unMarshall(byte[] res){
        ByteBuffer in = ByteBuffer.wrap(res);
        int len = 2;
        if(res.length == 11)
            len++;
        byte[] sin = new byte[len];
        in.get(sin, 0, len);
        code = new String(sin);
        if(code.equals("ok")){
            byte[] sin2 = new byte[11];
            in.get(sin2, 0, 11);
            spieler = new String(sin2);
        }
        return this;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer bbuf = ByteBuffer.allocate(13);
        bbuf.put(code.getBytes());
        bbuf.put(spieler.getBytes());
        buffer = bbuf.array();
        return buffer;
    }
}
