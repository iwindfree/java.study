package io.windfree.study.blockchain;

import io.windfree.util.CipherUtil;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.sql.Timestamp;

public class Transaction2 {
    String signature;
    PublicKey sender;
    PublicKey receiver;
    double amount;
    Timestamp timestamp;

    public  Transaction2(Wallet wallet, PublicKey receiver, double amount, String timestamp) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        this.sender = wallet.getPublicKey();
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = Timestamp.valueOf(timestamp);
        this.signature = wallet.sign(getData());

    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public PublicKey getSender() {
        return sender;
    }

    public void setSender(PublicKey sender) {
        this.sender = sender;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public void setReceiver(PublicKey receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<signature>:").append(this.signature).append("\n");
        sb.append(this.timestamp).append(" ");
        sb.append(CipherUtil.sha2562(this.sender.toString())).append("->");
        sb.append(CipherUtil.sha2562(this.receiver.toString())).append("  amount:").append(this.amount);
        return sb.toString();
    }

    public  String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.timestamp).append(" ");
        sb.append(CipherUtil.sha2562(this.sender.toString())).append("->");
        sb.append(CipherUtil.sha2562(this.receiver.toString())).append("  amount:").append(this.amount);
        return sb.toString();

    }


}
