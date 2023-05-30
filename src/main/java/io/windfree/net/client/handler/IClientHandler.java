package io.windfree.net.client.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface IClientHandler {
    public void execute (DataInputStream in, DataOutputStream out, byte[] buffer);
}
