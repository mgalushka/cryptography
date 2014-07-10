package com.maximgalushka.cryptography.week2;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

import static com.maximgalushka.cryptography.Tools.*;

/**
 * @since 7/10/2014.
 */
public class AES_CTR_Mode {

    private Cipher cipher;

    public AES_CTR_Mode(String key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] K = bytes(key);
        cipher = Cipher.getInstance("AES/ECB/NoPadding", "SunJCE");
        Key spec = new SecretKeySpec(K, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, spec);
    }

    public String decrypt(String text) throws BadPaddingException, IllegalBlockSizeException {
        byte[] full = bytes(text);
        System.out.println(encoded(full));

        int N = full.length;

        byte[] IV = new byte[16];
        System.arraycopy(full, 0, IV, 0, 16);

        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (16 * i < N) {
            byte[] encrypted = new byte[16];
            System.arraycopy(full, 16 * i, encrypted, 0, Math.min(16, full.length-16*i));
            i++;

            byte[] first = cipher.doFinal(IV);
            if (IV[IV.length - 1] < Byte.MAX_VALUE) {
                IV[IV.length - 1]++;
            } else {
                IV[IV.length - 1] = 0;
                IV[IV.length - 2]++;
            }
            String s = printSpaces(xor(encrypted, first));
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {

        //String T = "69dda8455c7dd4254bf353b773304eec0ec7702330098ce7f7520d1cbbb20fc388d1b0adb5054dbd7370849dbf0b88d393f252e764f1f5f7ad97ef79d59ce29f5f51eeca32eabedd9afa9329";
        //String key = "36f18357be4dbd77f050515c73fcf9f2";

        String key = "36f18357be4dbd77f050515c73fcf9f2";
        String T = "770b80259ec33beb2561358a9f2dc617e46218c0a53cbeca695ae45faa8952aa0e311bde9d4e01726d3184c34451";

        AES_CTR_Mode m = new AES_CTR_Mode(key);

        System.out.println(m.decrypt(T));
    }
}
