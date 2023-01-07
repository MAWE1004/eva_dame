package socket;

import java.nio.ByteBuffer;

public class SendClock {

    private byte altClock;  // muss man überhaupt über alt und neu unterscheiden?
    private byte neuClock;

    public SendClock(){

    }

    public SendClock(byte altClock, byte neuClock) {
        this.altClock = altClock;
        this.neuClock = neuClock;
    }

    public byte getAltClock() {
        return altClock;
    }

    public byte getNeuClock() {
        return neuClock;
    }

    public byte[]marshall() {
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(3);
        out.put(altClock);
        out.put(neuClock);
        buffer = out.array();
        return buffer;
    }

    public SendClock unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        byte[] sin = new byte[1];
        in.get(sin,0,1);
        altClock = in.get();
        neuClock = in.get();
        return this;
    }

    @Override
    public String toString() {
        return "SendClock{" +
                "altClock=" + altClock +
                ", neuClock=" + neuClock +
                '}';
    }
}
