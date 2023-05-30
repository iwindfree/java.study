package io.windfree.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer extends Thread{
    private int port = 10000; //default port
    private boolean running = true;
    private int threadPoolCount = 10;
    private ExecutorService executor;
    public TcpServer(int port) {
        this.port = port;
        this.setDaemon(true);
        this.setName("Thread-TCP Server");
    }

    public void run() {
            ServerSocket socket = null;
            executor = Executors.newFixedThreadPool(this.threadPoolCount);
        try {
            socket = new ServerSocket(this.port);
            while(this.running ){
                Socket client = socket.accept();

                executor.execute(new TcpServerWorker(client));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch(Exception ex) {
            }
        }

    }

    public void stopServer() {
        this.running = false;
    }

    public  void setThreadPoolCount(int count) {
        this.threadPoolCount = count;
    }
}
