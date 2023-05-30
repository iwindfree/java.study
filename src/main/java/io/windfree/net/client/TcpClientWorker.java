package io.windfree.net.client;

import io.windfree.net.client.handler.ByteMessageClientHandler;
import io.windfree.net.client.handler.IClientHandler;
import io.windfree.net.client.handler.StringMessageClientHandler;
import io.windfree.net.message.BytesMessage;
import io.windfree.net.message.StringMessage;
import io.windfree.util.FileUtil;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClientWorker implements  Runnable{
    private String server;
    private int port;
    private int connectionTimeOut = 2000;
    private int soTimeOut = 20000;
    private Socket socket = null;
    private boolean runnable = true;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    public TcpClientWorker(String ip, int port) {
        this.server = ip;
        this.port = port;
    }

    public  boolean initialize() {
        socket = new Socket();
        try{
            socket.connect(new InetSocketAddress(server,port),connectionTimeOut);
            socket.setSoTimeout(soTimeOut);

        }catch(Exception e) {
           return false;
        }
        return true;

    }

    private void execute() {
        try{
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            while(runnable) {
                int messageLen = input.readInt();
                int commandCode = input.readInt();
                byte[] buffer = new byte[messageLen - 8];
                input.readFully(buffer);
                IClientHandler handler = getHandler(commandCode);
                handler.execute(input,output,buffer);
            }

        }catch(Exception ex) {

        }finally {
            try {
                if (input != null) {
                     input.close();
                }
                if (output != null) {
                    output.close();
                }
            }catch(Exception e) {}
        }
    }

    private IClientHandler getHandler(int commandCode) {
        IClientHandler handler = null;
        switch(commandCode) {
            case 100:
                handler = new ByteMessageClientHandler();
                break;
            case 200:
                handler = new StringMessageClientHandler();
                break;
            case 300:
                stop();
                break;
            default:
                break;
        }
        return handler;
    }

    @Override
    public void run() {
        if(socket != null){
            execute();
        }
    }


    public void stop() {
        this.runnable = false;
    }

    public void SendMessage(){
        try {
            byte[] array = readFile(System.getProperty("user.dir") + "/test.txt");
            BytesMessage msg = new BytesMessage();
            msg.commandCode = 100;
            msg.value = array;
            output.write(msg.makePacket());
            output.flush();

        }catch(Exception ex) {

        }
    }

    public void SendMessage(String val){
        try {
            StringMessage msg = new StringMessage();
            msg.commandCode = 200;
            msg.value = val;
            output.write(msg.makePacket());
            output.flush();
        }catch(Exception ex) {

        }

    }

    /*private String readFile(String fileName) {
        File f = new File(fileName);
        String contents = FileUtil.load(f,"utf-8");
        return contents;

    }*/

    private byte[] readFile(String fileName) {
        File f = new File(fileName);
        return FileUtil.readAll(f);

    }
}
