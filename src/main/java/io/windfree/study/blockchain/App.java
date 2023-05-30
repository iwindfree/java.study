package io.windfree.study.blockchain;

import java.util.ArrayList;

public class App {
    public  static void main(String[] args) throws Exception {
        Wallet wallet1 = new Wallet();
        wallet1.readPrivateKey("private1.pem");
        wallet1.readPublicKey("public1.pem");

        Wallet wallet2 = new Wallet();
        wallet2.readPrivateKey("private2.pem");
        wallet2.readPublicKey("public2.pem");

        Wallet wallet3 = new Wallet();
        wallet3.readPrivateKey("private3.pem");
        wallet3.readPublicKey("public3.pem");

        Block2 block1 = new Block2(1,null,0,new ArrayList());
        block1.mining();
        System.out.println(block1);

        Block2 block2 = new Block2(2,block1.getBlockHash(),0,new ArrayList());
        block2.mining();
        System.out.println(block2);


        Transaction2 tx1 = new Transaction2(wallet1,wallet2.getPublicKey(),10,"2018-01-01 12:00:00");
        block2.addTransaction(tx1);
        Transaction2 tx2 = new Transaction2(wallet2,wallet3.getPublicKey(),10,"2018-01-01 12:10:00");
        block2.addTransaction(tx2);

        block2.mining();
        System.out.println(block2);




    }
}
