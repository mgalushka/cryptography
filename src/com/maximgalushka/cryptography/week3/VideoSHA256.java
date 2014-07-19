package com.maximgalushka.cryptography.week3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.maximgalushka.cryptography.Tools.encoded0;

/**
 * @author Maxim Galushka
 */
public class VideoSHA256 {

    private MessageDigest md;

    public VideoSHA256() throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance("SHA-256");
    }

    public byte[] sha256(byte[] in)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        md.reset();
        md.update(in);
        return md.digest();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException {
        VideoSHA256 v = new VideoSHA256();
        String PATH = "D:\\projects\\cryptography\\check.mp4";
        int BLOCK_SIZE = 1024;
        RandomAccessFile raf = new RandomAccessFile(PATH, "r");
        long L = raf.length();
        int remaining = (int) L % BLOCK_SIZE;
        int count = (int) L / BLOCK_SIZE;
        byte[] last = new byte[remaining];
        raf.seek(BLOCK_SIZE * count);
        raf.read(last);
        byte[] sha256 = v.sha256(last);
        count--;

        byte[] block = new byte[BLOCK_SIZE + 32];
        while (count >= 0) {
            raf.seek(BLOCK_SIZE * count);
            raf.read(block, 0, BLOCK_SIZE);
            System.arraycopy(sha256, 0, block, BLOCK_SIZE, 32);
            sha256 = v.sha256(block);
            count--;
        }
        byte[] h0 = sha256;
        System.out.println(encoded0(h0));

        //5b96aece304a1422224f9a41b228416028f9ba26b0d1058f400200f06a589949
    }


}
