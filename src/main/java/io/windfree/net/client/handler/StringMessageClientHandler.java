package io.windfree.net.client.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class StringMessageClientHandler implements  IClientHandler{
    @Override
    public void execute(DataInputStream in, DataOutputStream out, byte[] buffer) {
        String s = new String(buffer);
        System.out.println(("I got replied message from Server:" + s));
    }
}
