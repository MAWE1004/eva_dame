package socket;

import java.nio.ByteBuffer;

public class ResponseForAnmeldung {
    private String code;

    public ResponseForAnmeldung() {
    }

    public ResponseForAnmeldung(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public ResponseForAnmeldung unMarshall(byte[] res){
        ByteBuffer in = ByteBuffer.wrap(res);
        byte[] sin = new byte[2];
        in.get(sin, 0, 2);
        code = new String(sin);
        System.out.println("Ausgabe: " + code);
        if(code.equals("ok"))
            System.out.println("Anmeldung erfolgt");
        else
            System.out.println("Anmeldung fehlgeschlagen");

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