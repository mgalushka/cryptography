package com.maximgalushka.cryptography.week2;

import com.maximgalushka.cryptography.Rijndael;

/**
 * @since 7/10/2014.
 */
public class AES_CTR_Mode {

    private Rijndael aes;

    public AES_CTR_Mode(String key, String text) {
        this.aes = new Rijndael();
        aes.makeKey(new byte[0], 128, Rijndael.DIR_DECRYPT);
    }

    public String decrypt(){
        return null;
    }
}
