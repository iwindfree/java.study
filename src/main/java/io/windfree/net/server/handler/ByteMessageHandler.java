package io.windfree.net.server.handler;

import io.windfree.net.message.StringMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ByteMessageHandler extends  Thread implements  IHandler {
    private byte[] buff;
    private DataInputStream input;
    private  DataOutputStream output;
    @Override
    public void execute(DataInputStream in, DataOutputStream out, byte[] buffer) {
        this.buff = buffer;
        this.input = in;
        this.output = out;
        this.setDaemon(true );
        this.setName("Thread-ConsoleOutputHandler");
        this.start();

    }

    public void run() {
        try {
            String value = new String(this.buff);
            System.out.println(value);
            StringMessage msg = new StringMessage();
            msg.commandCode = 200;
            msg.value = "I got your message.";
            output.write(msg.makePacket());
            output.flush();
        }catch(Exception ex) {}
    }


}
