package com.maximgalushka.cryptography.week4;

import java.io.UnsupportedEncodingException;

import static com.maximgalushka.cryptography.Tools.printSpaces;

/**
 * @since 7/28/2014.
 */
public class Bytes2 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = {(byte) 195, (byte) 9};
        System.out.println(new String(bytes, "US-ASCII"));
        System.out.println(printSpaces(bytes));
    }
}
