package io.windfree.util;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ECUtil {
    public static void generate(String privateKeyName, String publcKeyName) throws InvalidAlgorithmParameterException, IOException, NoSuchProviderException, NoSuchAlgorithmException {
        /*
        Security.addProvider(new BouncyCastlePQCProvider());
        KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDSA","BC");
        */
        //KeyPairGenerator generator = KeyPairGenerator.getInstance("EC","SunEC");
        //ECGenParameterSpec ecsp = new ECGenParameterSpec("sect163k1");
        //generator.initialize(ecsp,new SecureRandom());

        KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        generator.initialize(1024, random);
        KeyPair keyPair = generator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

      //  PemUtil.write(privateKey,"DSA PRIVATE KEY",privateKeyName);
     //   PemUtil.write(publicKey,"DSA PUBLIC KEY",publcKeyName);
    }

    public  static  PrivateKey readPrivateKey(String fileName) throws Exception {
        String data = readString(fileName);
        data = data.replaceAll("-----BEGIN DSA PRIVATE KEY-----", "");
        data = data.replaceAll("-----END DSA PRIVATE KEY-----", "");
        // PEM �뙆�씪�� Base64濡� �씤肄붾뵫 �릺�뼱�엳�쑝誘�濡� �뵒肄붾뵫�빐�꽌 �씫�쓣 �닔 �엳�룄濡� �빀�땲�떎.
        //byte[] decoded = Base64Util.decode(data);
        byte[] decoded = Base64.decode(data);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("DSA","SUN");
        PrivateKey privateKey = factory.generatePrivate(spec);
        return privateKey;
    }

    public  static PublicKey readPublicKey(String fineName) throws Exception {
        String data = readString(fineName);
        // 遺덊븘�슂�븳 �꽕紐� 援щЦ�쓣 �젣嫄고빀�땲�떎.
        data = data.replaceAll("-----BEGIN DSA PUBLIC KEY-----", "");
        data = data.replaceAll("-----END DSA PUBLIC KEY-----", "");
        // PEM �뙆�씪�� Base64濡� �씤肄붾뵫 �릺�뼱�엳�쑝誘�濡� �뵒肄붾뵫�빐�꽌 �씫�쓣 �닔 �엳�룄濡� �빀�땲�떎.
        //byte[] decoded = Base64Util.decode(data);
        byte[] decoded = Base64.decode(data);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("DSA","SUN");
        PublicKey publicKey = factory.generatePublic(spec);
        return publicKey;
    }


    private static String readString(String filename) throws FileNotFoundException, IOException {
        String pem = "";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null)
            pem += line + "\n";
        br.close();
        return pem;
    }


    public  static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchProviderException, IOException {
        ECUtil.generate("private1.pem","public1.pem");
        ECUtil.generate("private2.pem","public2.pem");

        try {
            PrivateKey privateKey1 = ECUtil.readPrivateKey("private1.pem");
            PublicKey publicKey1 = ECUtil.readPublicKey("public1.pem");
            PrivateKey privateKey2 = ECUtil.readPrivateKey("private2.pem");
            PublicKey publicKey2 = ECUtil.readPublicKey("public2.pem");

            Signature signature = Signature.getInstance("DSA","SUN");
            signature.initSign(privateKey1);

            String text = "�룊臾몄엯�땲�떎.";
            System.out.println("�룊臾몄젙蹂�:" + text);
            byte[] textArr = text.getBytes("UTF-8");

            // �븫�샇�솕�븯�뿬異쒕젰
            signature.update(textArr);
            byte[] signatureArr = signature.sign();
            System.out.println("�꽌紐낅맂 媛�: 0x" + HexaUtil.byteArrayToHexString(signatureArr).toUpperCase());


            Signature signature2 = Signature.getInstance("DSA","SUN");
            signature2.initVerify(publicKey1);
            signature2.update(textArr);
            boolean result = signature2.verify(signatureArr);
            //boolean result = signature2.verify(signatureArr);
            if(result) {
                System.out.println("OK");
            } else {
                System.out.println("NOT OK");
            }




        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
