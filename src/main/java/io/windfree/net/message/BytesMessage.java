package io.windfree.net.message;

import io.windfree.util.ByteUtil;

public class BytesMessage implements IMessage{
    public int commandCode;
    public byte[] value;
    @Override
    public byte[] makePacket() {
        int offset = 0;
        int valueLen = value.length;
        int messageLen = 4 + 4 + valueLen; // (messageLen + commandCode + value)
        byte[] sendBytes = new byte[messageLen];
        byte[] lenBytes = ByteUtil.getBytes(messageLen);
        byte[] cmdBytes = ByteUtil.getBytes(this.commandCode);
        System.arraycopy(lenBytes,0,sendBytes,offset,4);
        offset += 4;
        System.arraycopy(cmdBytes,0,sendBytes,offset,4);
        offset += 4;
        System.arraycopy(value,0,sendBytes,offset,valueLen);
        return sendBytes;
    }
}
