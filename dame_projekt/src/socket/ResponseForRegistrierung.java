package socket;

import java.nio.ByteBuffer;

public class ResponseForRegistrierung {
    private String code;

    public ResponseForRegistrierung(){
    }

    public ResponseForRegistrierung(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public ResponseForRegistrierung unMarshall(byte[] res){
        ByteBuffer in = ByteBuffer.wrap(res);
        byte[] sin = new byte[2];
        in.get(sin, 0, 2);
        code = new String(sin);
        System.out.println("Ausgabe: " + code);
        if(code.equals("ok"))
            System.out.println("Registrierung erfolgt");
        else
            System.out.println("Registrierung fehlgeschlagen");

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