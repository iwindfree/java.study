package io.windfree.net.simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer extends  Thread{
    private int port = 10000; //default port
    private boolean running = true;
    private int threadPoolCount = 10;
    private ExecutorService executor;
    public TcpServer(int port) {
        this.port = port;
        this.setDaemon(true);
        this.setName("Thread-TCP Server");
    }

    @Override
    public void run() {
        ServerSocket socket = null;
        executor = Executors.newFixedThreadPool(this.threadPoolCount);
        try {
            socket = new ServerSocket(this.port);
            while (this.running) {
                Socket client = socket.accept();
                executor.execute(new ServerWorker(client));
            }
        }catch (Exception ex) {}
    }

    class ServerWorker  implements Runnable{
        private Socket clientSocket;
        //private DataInputStream din;
        //private DataOutputStream dout;
        private BufferedReader reader;
        private BufferedWriter writer;
        public  ServerWorker(Socket socket) {
            try {
                this.clientSocket = socket;
               // this.din = new DataInputStream(new BufferedInputStream(this.clientSocket.getInputStream()));
               // this.dout = new DataOutputStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));
                this.reader = new BufferedReader(new InputStreamReader( this.clientSocket.getInputStream()));
                this.writer = new BufferedWriter(new OutputStreamWriter( this.clientSocket.getOutputStream()));

            }catch (Exception ex) {}
        }
        @Override
        public void run() {
                try {
                    this.writer.write("this is a message from server");
                    this.writer.newLine();
                    this.writer.write("this is a message2 from server");
                    this.writer.newLine();
                    this.writer.write("this is a message3 from server");
                    this.writer.newLine();
                    this.writer.write("Quit");
                    this.writer.newLine();
                    this.writer.flush();
                    clientSocket.close();
                }catch(Exception ex) {}
        }
    }

    public  static  void main(String[] args) {
        TcpServer server = new TcpServer(6000);
        server.start();
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
