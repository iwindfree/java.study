package io.windfree.study.blockchain;

import io.windfree.util.CipherUtil;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private int blockId;
    private  int nonce;
    private List<Transaction> txList;
    private  String prevBlockHash;


    public  Block(int blockID, String prevHash, int nonce, List<Transaction> txList) {
        setBlockId(blockID);
        setNonce(nonce);
        setTxList(txList);
        setPrevBlockHash(prevHash);
    }
    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public List<Transaction> getTxList() {
        return txList;
    }

    public void setTxList(List<Transaction> txList) {
        this.txList = txList;
    }

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public  void addTransaction(Transaction tx) {
        this.txList.add(tx);
    }

    public  String getBlockHash() {
        StringBuilder sb = new StringBuilder();
        for(Transaction tx : txList) {
           sb.append(tx.toString());
        }
        return CipherUtil.sha2562(nonce + sb.toString() + prevBlockHash);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("블록번호:").append(getBlockId()).append("\n");
        sb.append("이전블록 해시:").append(getPrevBlockHash()).append("\n");
        sb.append("채굴변수값:").append(getNonce()).append("\n");
        sb.append("트랜잭션 개수").append(txList.size()).append("\n");
        for(Transaction tx : txList) {
            sb.append("트랜잭션 정보:").append(tx).append("\n");
        }
        sb.append("블록해시:").append(getBlockHash()).append("\n");
        return  sb.toString();
    }

    public void mining() {
        while(true){
            if(getBlockHash().substring(0,4).equals("0000")) {
                System.out.println(blockId + "번째 블록 체굴에 성공하였습니다.");
                break;
            }
            nonce++;
        }

    }

    public  static void main(String[] args) {
        Block block1 = new Block(1, null, 0, new ArrayList());
        block1.mining();

        Block block2 = new Block(2, block1.getBlockHash(), 0, new ArrayList());
        block2.addTransaction(new Transaction("나동빈", "박한울", 1.5));
        block2.addTransaction(new Transaction("이태일", "박한울", 0.7));
        block2.mining();

        Block block3 = new Block(3, block2.getBlockHash(), 0, new ArrayList());
        block3.addTransaction(new Transaction("강종구", "이상욱", 8.2));
        block3.addTransaction(new Transaction("박한울", "나동빈", 0.4));
        block3.mining();

        Block block4 = new Block(4, block3.getBlockHash(), 0, new ArrayList());
        block4.addTransaction(new Transaction("이상욱", "강종구", 0.1));
        block4.mining();

        System.out.println(block1);
        System.out.println(block2);
        System.out.println(block3);
        System.out.println(block4);
    }
}

