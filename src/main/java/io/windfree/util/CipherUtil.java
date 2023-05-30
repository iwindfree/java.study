package io.windfree.util;

import java.security.MessageDigest;

public class CipherUtil {
    public static String sha256(String plainText) {
        String salt = "qwertyuiop!@#$%^&*()zxcvbnm,.";
        String sha256Text = null;
        if (plainText != null) {
            try {
                MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
                sha256.update(salt.getBytes());
                byte[] byteArray = plainText.getBytes();
                byte[] sha256Bytes = sha256.digest(byteArray);
                StringBuffer buf = new StringBuffer();

                for (int i = 0; i < sha256Bytes.length; i++) {
                    if ((sha256Bytes[i] & 0xff) < 0x10) {
                        buf.append("0");
                    }
                    buf.append(Long.toString(sha256Bytes[i] & 0xff, 16));
                }

                sha256Text = buf.toString();
            } catch (Throwable t) {
                return plainText;
            }
        }
        return sha256Text;
    }

    public static String sha2562(String input) {
        StringBuffer result = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            byte bytes[] = md.digest();
            for(int i = 0; i < bytes.length; i++) {
                    result.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }




    public static void main(String[] args) {
        int nonce = 0;
        while(true){
            if(CipherUtil.sha256(nonce + "").substring(0,6).equals("000000")) {
                System.out.println("find answer , iterate:" + nonce);
                break;
            }
            nonce++;
        }
    }
}
