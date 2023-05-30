package io.windfree.study.blockchain;

import io.windfree.util.ECUtil;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class PemGen {
    public static void generate() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchProviderException, IOException {
        ECUtil.generate("private1.pem","public1.pem");
        ECUtil.generate("private2.pem","public2.pem");
        ECUtil.generate("private3.pem","public3.pem");

    }

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        PemGen.generate();;
    }

}
