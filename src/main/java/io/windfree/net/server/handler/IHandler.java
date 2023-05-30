package io.windfree.net.server.handler;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;

public interface IHandler {
    public void execute (DataInputStream in, DataOutputStream out, byte[] buffer);
}
