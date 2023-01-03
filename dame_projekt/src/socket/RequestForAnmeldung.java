package socket;

import java.nio.ByteBuffer;

public class RequestForAnmeldung {
    private String name;
    private String password;

    public RequestForAnmeldung(){
    }

    public RequestForAnmeldung(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(25);
        out.put("anm".getBytes());
        out.put((byte) name.length());
//        System.out.println("LÄNGE: " + name.length() + "in byte : " + (byte) name.length());
        out.put(name.getBytes());
        out.put((byte) password.length());
        out.put(password.getBytes());
        buffer = out.array();
        System.out.println("Marshall: " + new String(buffer));
        return buffer;
    }

    public RequestForAnmeldung unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        byte len = in.get();
        System.out.println("BYTE: " + len);
        byte[] sin = new byte[len];
        in.get(sin, 0, len);
        name = new String(sin);
        System.out.println("NAME: " + name);
        len = in.get();
        System.out.println("BYTE: " + len);
        sin = new byte[len];
        in.get(sin, 0, len);
        password = new String(sin);
        System.out.println("PASSW: " + password);
        return this;
    }
}








