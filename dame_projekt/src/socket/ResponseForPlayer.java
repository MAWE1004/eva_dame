package socket;

import java.nio.ByteBuffer;

public class ResponseForPlayer {
    private String code;
    private String spiel;
    private String gegner;
    private String farbe;

    public ResponseForPlayer(){
    }

    public ResponseForPlayer(String code, String spiel, String gegner, String farbe) {
        this.code = code;
        this.spiel = spiel;
        this.gegner = gegner;
        this.farbe = farbe;
    }

    public String getCode() {
        return code;
    }

    public String getSpiel() {
        return spiel;
    }

    public String getGegner() {
        return gegner;
    }

    public String getFarbe() {
        return farbe;
    }

    public ResponseForPlayer unMarshall(byte[] res){
        ByteBuffer in = ByteBuffer.wrap(res);
        int len = 2;
        if(res.length == 20)
            len++;
        byte[] sin = new byte[len];
        in.get(sin, 0, len);
        code = new String(sin);
        if(code.equals("bad"))
            return this;

        len = in.get();
        sin = new byte[len];
        in.get(sin, 0, len);
        spiel = new String(sin);

        len = in.get();
        sin = new byte[len];
        in.get(sin, 0, len);
        gegner = new String(sin);

        sin = new byte[1];
        in.get(sin, 0, 1);
        spiel = new String(sin);


        return this;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer bbuf = ByteBuffer.allocate(20);
        bbuf.put(code.getBytes());
        bbuf.put((byte) spiel.length());
        bbuf.put(spiel.getBytes());
        bbuf.put((byte) gegner.length());
        bbuf.put(gegner.getBytes());
        bbuf.put(farbe.getBytes());

        buffer = bbuf.array();
        return buffer;
    }
}
