package io.windfree.net.server;

import io.windfree.net.server.handler.ByteMessageHandler;
import io.windfree.net.server.handler.DefaultHandler;
import io.windfree.net.server.handler.IHandler;

import java.io.*;
import java.net.Socket;


public class TcpServerWorker implements  Runnable{
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;

    public TcpServerWorker(Socket sock) {
        this.socket = sock;
        try {
            this.socket.setSoTimeout(20000);
            this.socket.setReuseAddress(true);
            din = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
            dout = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));

        }catch(Exception ex){}

    }

    // 데이터 전문구조
    // | Message Len(4byte) | CommandCode ( 4byte) | Body (n byte) |
    @Override
    public void run() {
        try {
            while (true) {
                /*
                byte[] buffer = new byte[10];
                din.readFully(buffer);
                */
                int messageLen = din.readInt();
                int commandCode = din.readInt();
                byte[] buffer = new byte[messageLen - 8];
                din.readFully(buffer);

                IHandler handler = getHandler(commandCode);
                handler.execute( din, dout, buffer);
            }
        } catch (Exception ex) {

        } finally {
            try {
               din.close();
               dout.close();
            } catch (Exception e) {
                System.out.println("connectin is closed");
            }

        }
    }


    private IHandler getHandler(int commandCode) {
        IHandler handler = null;
        switch(commandCode){
            case 100:
                handler =  new ByteMessageHandler();
                break;
            default:
                handler = new DefaultHandler();

        }
        return handler;
    }

    public void stopServer() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
