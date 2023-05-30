package io.windfree.net.simple;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClient extends  Thread{
    private String server;
    private int port;
    private int connectionTimeOut = 2000;
    private int soTimeOut = 20000;
    private Socket socket = null;
    private boolean runnable = true;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    public TcpClient(String ip, int port) {
        this.server = ip;
        this.port = port;
    }

    public  boolean initialize() {
        socket = new Socket();
        try{
            socket.connect(new InetSocketAddress(server,port),connectionTimeOut);
            socket.setSoTimeout(soTimeOut);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        }catch(Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public void run() {
        try {
            String msg;
            do {
                msg = reader.readLine();
                System.out.println(msg);
            }while (!msg.equals("Quit")); // server 가 연결을 끊는 경우는 msq != null 로 체크
            System.out.println("close connection");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static void main(String[] args) {
        TcpClient client = new TcpClient("127.0.0.1",6000);
        client.initialize();
        client.start();

    }
}
