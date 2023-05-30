package io.windfree.study.blockchain;

public class Transaction {
    String sender;
    String receiver;
    double amount;

    public Transaction(String sender, String receiver, double amount) {
        setReceiver(receiver);
        setAmount(amount);
        setSender(sender);
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public  String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(sender).append("가 ").append(receiver).append("에게 ").append(amount).append("를 송금하였습니다.");
        return sb.toString();
    }

    public  static void main(String[] args) {
        Transaction tx = new Transaction("A","B", 1500);
        System.out.println(tx);
    }
}

