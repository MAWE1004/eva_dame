package socket;

import java.nio.ByteBuffer;

public class RequestForZug {
    private String spiel;
    private String farbe;
    private byte altX, altY;
    private byte neuX, neuY;
    private byte schlagenX, schlagenY;

    public RequestForZug(){
    }

    public RequestForZug(String spiel, String farbe, byte altX, byte altY, byte neuX, byte neuY, byte schlagenX, byte schlagenY) {
        this.spiel = spiel;
        this.farbe = farbe;
        this.altX = altX;
        this.altY = altY;
        this.neuX = neuX;
        this.neuY = neuY;
        this.schlagenX = schlagenX;
        this.schlagenY = schlagenY;
    }

    public RequestForZug(String farbe, byte altX, byte altY, byte neuX, byte neuY, byte schlagenX, byte schlagenY) {
        this.farbe = farbe;
        this.altX = altX;
        this.altY = altY;
        this.neuX = neuX;
        this.neuY = neuY;
        this.schlagenX = schlagenX;
        this.schlagenY = schlagenY;
    }

    public String getSpiel() {
        return spiel;
    }

    public String getFarbe() {
        return farbe;
    }

    public byte getAltX() {
        return altX;
    }

    public byte getAltY() {
        return altY;
    }

    public byte getNeuX() {
        return neuX;
    }

    public byte getNeuY() {
        return neuY;
    }

    public byte getSchlagenX() {
        return schlagenX;
    }

    public byte getSchlagenY() {
        return schlagenY;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(16);
        out.put("zug".getBytes());
        out.put((byte) spiel.length());
        out.put(spiel.getBytes());
        out.put(farbe.getBytes());
        out.put(altX);
        out.put(altY);
        out.put(neuY);
        out.put(neuY);
        out.put(schlagenX);
        out.put(schlagenY);
        buffer = out.array();
        return buffer;
    }

    public RequestForZug unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        int len = in.get();
        byte[] sin = new byte[len];
        in.get(sin, 0, len);
        spiel = new String(sin);
        farbe = String.valueOf(in.get());
        altX = in.get();
        altY = in.get();
        neuX = in.get();
        neuY = in.get();
        schlagenX = in.get();
        schlagenY = in.get();
        return this;
    }

    @Override
    public String toString() {
        return "RequestForZug{" +
                "altX=" + altX +
                ", altY=" + altY +
                ", neuX=" + neuX +
                ", neuY=" + neuY +
                ", schlagenX=" + schlagenX +
                ", schlagenY=" + schlagenY +
                '}';
    }
}
