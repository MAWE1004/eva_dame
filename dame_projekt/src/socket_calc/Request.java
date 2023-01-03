package socket_calc;

import java.nio.ByteBuffer;

public class Request {
    private double a, b;
    private String op;

    Request(){

    }
    Request(double a, double b, String op){
        this.a = a;
        this.b = b;
        this.op = op;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public String getOp() {
        return op;
    }

    byte[] marshall(){
        byte[] buffer;
        ByteBuffer out = ByteBuffer.allocate(19);
        out.putDouble(a);
        out.putDouble(b);
        out.put(op.getBytes());
        buffer = out.array();
        return buffer;
    }

    Request unMarshall(byte[] req){
        ByteBuffer in = ByteBuffer.wrap(req);
        a = in.getDouble();
        b = in.getDouble();
        byte[] sin = new byte[3];
        in.get(sin, 0, 3);
        op = new String(sin);
        return this;
    }
}
