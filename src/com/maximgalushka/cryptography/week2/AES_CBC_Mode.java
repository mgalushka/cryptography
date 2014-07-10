package com.maximgalushka.cryptography.week2;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

import static com.maximgalushka.cryptography.Tools.*;

/**
 * @since 7/10/2014.
 */
public class AES_CBC_Mode {

    private Cipher cipher;

    public AES_CBC_Mode(String key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] K = bytes(key);
        cipher = Cipher.getInstance("AES/ECB/NoPadding", "SunJCE");
        Key spec = new SecretKeySpec(K, "AES");
        cipher.init(Cipher.DECRYPT_MODE, spec);
    }

    public String decrypt(String text) throws BadPaddingException, IllegalBlockSizeException {
        byte[] full = bytes(text);
        System.out.println(encoded(full));

        int N = full.length;

        byte[] IV = new byte[16];
        System.arraycopy(full, 0, IV, 0, 16);

        byte[] prev = IV;

        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (16 * i < N) {
            byte[] encrypted = new byte[16];
            System.arraycopy(full, 16 * i, encrypted, 0, Math.min(16, full.length - 16 * i));
            i++;

            byte[] coded = cipher.doFinal(encrypted);
            sb.append(printSpaces(xor(coded, prev)));

            prev = encrypted;
        }
        return sb.toString();
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {

        String T1 = "4ca00ff4c898d61e1edbf1800618fb2828a226d160dad07883d04e008a7897ee2e4b7465d5290d0c0e6c6822236e1daafb94ffe0c5da05d9476be028ad7c1d81";
        String key1 = "140b41b22a29beb4061bda66b6747e14";

        String T2 = "5b68629feb8606f9a6667670b75b38a5b4832d0f26e1ab7da33249de7d4afc48e713ac646ace36e872ad5fb8a512428a6e21364b0c374df45503473c5242a253";
        String key2 = "140b41b22a29beb4061bda66b6747e14";

        AES_CBC_Mode m = new AES_CBC_Mode(key1);
        System.out.println(m.decrypt(T1));

        m = new AES_CBC_Mode(key2);
        System.out.println(m.decrypt(T2));
    }
}
