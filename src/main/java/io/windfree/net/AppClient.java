package io.windfree.net;

import io.windfree.net.client.TcpClientWorker;

public class AppClient {
    public static  void main(String[] args) {
        TcpClientWorker worker = new TcpClientWorker("127.0.0.1",7000);
        if(worker.initialize()) {
            Thread th = new Thread(worker);
            th.setDaemon(true);
            th.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.SendMessage();
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }




    }
}
