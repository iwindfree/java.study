package io.windfree.study.blockchain;

import io.windfree.util.CipherUtil;
import io.windfree.util.HexaUtil;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.List;

public class Block2 {
    private int blockId;
    private  int nonce;
    private List<Transaction2> txList;
    private  String prevBlockHash;
    private  final String ALGORITHM = "DSA";

    public Block2(int blockID, String prevHash, int nonce, List<Transaction2> txList) {
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

    public List<Transaction2> getTxList() {
        return txList;
    }

    public void setTxList(List<Transaction2> txList) {
        this.txList = txList;
    }

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }


    public  String getBlockHash() {
        StringBuilder sb = new StringBuilder();
        for(Transaction2 tx : txList) {
           sb.append(tx.toString());
        }
        return CipherUtil.sha2562(nonce + sb.toString() + prevBlockHash);
    }

    public boolean verifyTransaction(Transaction2 tx) throws NoSuchProviderException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(ALGORITHM);
        byte[] arr = tx.getData().getBytes("UTF-8");
        signature.initVerify(tx.getSender());
        signature.update(arr);
        return signature.verify(HexaUtil.hexStringToByteArray(tx.getSignature()));
    }

    public  void addTransaction(Transaction2 tx) throws NoSuchAlgorithmException, SignatureException, NoSuchProviderException, InvalidKeyException, UnsupportedEncodingException {
        if(verifyTransaction(tx)) {
            System.out.println("Good tx");
            txList.add(tx);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("블록번호:").append(getBlockId()).append("\n");
        sb.append("이전블록 해시:").append(getPrevBlockHash()).append("\n");
        sb.append("채굴변수값:").append(getNonce()).append("\n");
        sb.append("트랜잭션 개수").append(txList.size()).append("\n");
        for(Transaction2 tx : txList) {
            sb.append("트랜잭션 정보:").append("\n").append(tx).append("\n");
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
        /*
        Block2 block1 = new Block2(1, null, 0, new ArrayList());
        block1.mining();

        Block2 block2 = new Block2(2, block1.getBlockHash(), 0, new ArrayList());
        block2.addTransaction(new Transaction2("나동빈", "박한울", 1.5));
        block2.addTransaction(new Transaction("이태일", "박한울", 0.7));
        block2.mining();

        Block2 block3 = new Block2(3, block2.getBlockHash(), 0, new ArrayList());
        block3.addTransaction(new Transaction("강종구", "이상욱", 8.2));
        block3.addTransaction(new Transaction("박한울", "나동빈", 0.4));
        block3.mining();

        Block2 block4 = new Block2(4, block3.getBlockHash(), 0, new ArrayList());
        block4.addTransaction(new Transaction("이상욱", "강종구", 0.1));
        block4.mining();

        System.out.println(block1);
        System.out.println(block2);
        System.out.println(block3);
        System.out.println(block4);
        */
    }
}

