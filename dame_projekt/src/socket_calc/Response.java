package socket_calc;

import java.nio.ByteBuffer;

public class Response {
    private double result;
    private String code;

    Response(){
    }

    public Response(double result, String code) {
        this.result = result;
        this.code = code;
    }

    public double getResult() {
        return result;
    }

    public String getCode() {
        return code;
    }

    public Response unMarshall(byte[] res){
        ByteBuffer in = ByteBuffer.wrap(res);
        int len = 2;
        if(res.length == 11)
            len++;
        byte[] sin = new byte[len];
        in.get(sin, 0, len);
        code = new String(sin);
        if(code.equals("ok"))
            result = in.getDouble();
        else
            code = "bad";
        return this;
    }

    public byte[] marshall(){
        byte[] buffer;
        ByteBuffer bbuf = ByteBuffer.allocate(11);
        bbuf.put(code.getBytes());
        bbuf.putDouble(result);
        buffer = bbuf.array();
        return buffer;
    }
}
