package io.windfree.util;

public class ByteUtil {
    public static byte[] getBytes(int v ){
        byte buf[] = new byte[4];
        buf[0] = (byte) ((v >>> 24) & 0xFF);
        buf[1] = (byte) ((v >>> 16) & 0xFF);
        buf[2] = (byte) ((v >>> 8) & 0xFF);
        buf[3] = (byte) ((v >>> 0) & 0xFF);
        return buf;
    }
    public static int toInt(byte[] buf, int pos) {
        int ch1 = buf[pos] & 0xff;
        int ch2 = buf[pos + 1] & 0xff;
        int ch3 = buf[pos + 2] & 0xff;
        int ch4 = buf[pos + 3] & 0xff;
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
    }



}




