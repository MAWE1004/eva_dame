package socket;

import java.nio.ByteBuffer;

public class RequestForPlayer {
    private String spieler;
    private byte time;

    RequestForPlayer(){
    }

    public RequestForPlayer(String spieler, byte time) {
        this.spieler = spieler;
        this.time = time;
    }

    public String getSpieler(){
        return spieler;
    }

    public byte getTime() {
        return time;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(16);
        out.put("spi".getBytes());
        out.put(time);
        out.put((byte) spieler.length());
        out.put(spieler.getBytes());
        buffer = out.array();
        System.out.println("Marshall Suche: " + new String(buffer));
        return buffer;
    }

    public RequestForPlayer unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        time = in.get();
        byte len = in.get();
        byte[] sin = new byte[len];
        in.get(sin, 0, len);
        spieler = new String(sin);
        return this;
    }

}
