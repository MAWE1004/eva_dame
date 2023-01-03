package socket;

import java.nio.ByteBuffer;

public class RequestForPlayer {
    private String spieler;

    RequestForPlayer(){
    }

    RequestForPlayer(String spieler){
        this.spieler = spieler;
    }

    public String getSpieler(){
        return spieler;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(11);
        out.put(spieler.getBytes());
        buffer = out.array();
        return buffer;
    }

    public RequestForPlayer unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        byte[] sin = new byte[11];
        in.get(sin, 0, 11);
        spieler = new String(sin);
        return this;
    }

}
