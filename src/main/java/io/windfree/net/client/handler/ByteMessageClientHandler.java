package io.windfree.net.client.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ByteMessageClientHandler implements  IClientHandler{
    @Override
    public void execute(DataInputStream in, DataOutputStream out, byte[] buffer) {
        String message = new String(buffer);
        System.out.println("Received from Server :" + message);
    }
}
