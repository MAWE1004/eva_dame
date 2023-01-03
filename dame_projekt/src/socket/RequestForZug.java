package socket;

import java.nio.ByteBuffer;

public class RequestForZug {
    private byte altX, altY;
    private byte neuX, neuY;
    private byte schlagenX, schlagenY;

    public RequestForZug(){
    }

    public RequestForZug(byte altX, byte altY, byte neuX, byte neuY, byte schlagenX, byte schlagenY) {
        this.altX = altX;
        this.altY = altY;
        this.neuX = neuX;
        this.neuY = neuY;
        this.schlagenX = schlagenX;
        this.schlagenY = schlagenY;
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
        ByteBuffer out = ByteBuffer.allocate(10);
        out.put("zug".getBytes());
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
