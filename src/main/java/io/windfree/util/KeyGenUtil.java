package io.windfree.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class KeyGenUtil {
    public static void generate(String msg ) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC","SunEC");
            ECGenParameterSpec ecsp = new ECGenParameterSpec("sect163k1");
            keyPairGenerator.initialize(ecsp,new SecureRandom());

            KeyPair keyPair = keyPairGenerator.genKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            Signature signature = Signature.getInstance("SHA1withECDSA","SunEC");
            signature.initSign(privateKey);

            signature.update(msg.getBytes("UTF-8"));
            byte[] signatureByte = signature.sign();
            String encryMsg = new BigInteger(1,signatureByte).toString(16).toUpperCase();
            System.out.println("암호문:Ox" + encryMsg);

            Signature signature2 = Signature.getInstance("SHA1withECDSA","SunEC");
            signature2.initVerify(publicKey);
            signature2.update(msg.getBytes("UTF-8"));
            boolean result = signature2.verify(signatureByte);
            if(result) {
                System.out.println("OK");
            } else {
                System.out.println("NOT OK");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public  static void main(String[] args) {
        KeyGenUtil.generate("I am a boy");
    }
}
