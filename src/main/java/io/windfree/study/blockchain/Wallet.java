package io.windfree.study.blockchain;

import io.windfree.util.ECUtil;
import io.windfree.util.HexaUtil;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class Wallet {
    private  final String ALGORITHM = "DSA";
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public  Wallet() {

    }

    public  void readPrivateKey(String fileName) throws Exception {
        this.privateKey = ECUtil.readPrivateKey(fileName);
    }

    public  void readPublicKey(String fileName) throws Exception {
        this.publicKey = ECUtil.readPublicKey(fileName);
    }

    public String sign(String data) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initSign(this.privateKey);
        byte[] arrText = data.getBytes("UTF-8");
        signature.update(arrText);
        byte[] arrSignature = signature.sign();
        return HexaUtil.byteArrayToHexString(arrSignature);
        //return (new BigInteger(1,arrSignature).toString(16)).toUpperCase();
    }



}
