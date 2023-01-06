package socket;

import java.nio.ByteBuffer;

public class SendZug {
    String farbe;
    private byte altX, altY;
    private byte neuX, neuY;
    private byte schlagenX, schlagenY;

    public SendZug(){
    }

    public SendZug(String farbe, byte altX, byte altY, byte neuX, byte neuY, byte schlagenX, byte schlagenY) {
        this.farbe = farbe;
        this.altX = altX;
        this.altY = altY;
        this.neuX = neuX;
        this.neuY = neuY;
        this.schlagenX = schlagenX;
        this.schlagenY = schlagenY;
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
        out.put(farbe.getBytes());
        out.put(altX);
        out.put(altY);
        out.put(neuX);
        out.put(neuY);
        out.put(schlagenX);
        out.put(schlagenY);
        buffer = out.array();
        return buffer;
    }

    public SendZug unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        byte[] sin = new byte[1];
        in.get(sin,0,1);
        farbe = new String(sin);
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
        return "sendZug{" +
                "farbe='" + farbe + '\'' +
                ", altX=" + altX +
                ", altY=" + altY +
                ", neuX=" + neuX +
                ", neuY=" + neuY +
                ", schlagenX=" + schlagenX +
                ", schlagenY=" + schlagenY +
                '}';
    }
}
