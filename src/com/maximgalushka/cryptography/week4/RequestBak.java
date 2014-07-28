package com.maximgalushka.cryptography.week4;

import com.maximgalushka.cryptography.Tools;
import com.maximgalushka.http.HttpHelper;
import com.maximgalushka.http.NoAuthSessionClient;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.maximgalushka.cryptography.Tools.*;
import static com.maximgalushka.cryptography.Tools.printSpaces;

/**
 * @since 7/28/2014.
 */
public class RequestBak {

    private static final Logger log = Logger.getLogger(Request_0.class);

    protected static Map<Integer, byte[]> pads() {
        Map<Integer, byte[]> pads = new HashMap<Integer, byte[]>((int) Math.ceil(16 * 0.75) + 1);
        for (int i = 1; i <= 16; i++) {
            byte[] bts = new byte[i];
            Arrays.fill(bts, (byte) i);
            pads.put(i, bts);
        }
        return pads;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        NoAuthSessionClient client = new NoAuthSessionClient("crypto-class.appspot.com", 80, "http");
        HttpHelper helper = new HttpHelper<String>(client.getNewSessionClient());
        String M = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4";
        byte[] bytes = bytes(M);
        int L = bytes.length;
        Map<Integer, byte[]> pads = pads();
        byte[] decoded = new byte[16];
        boolean fail = false;
        for (int interestingByte = 0; interestingByte < 16; interestingByte++) {
            if (fail) break;
            fail = true;
            log.debug(String.format("Started analysing [%d] byte", interestingByte + 1));
            byte[] pad = pads.get(interestingByte + 1);

            for (int guess = 0; guess <= 255; guess++) {
                byte[] bt = Arrays.copyOfRange(bytes, L - 17 - interestingByte, L - 16);
                if (bt.length != interestingByte + 1) throw new RuntimeException("Incorrect size!");
                bt = xor(bt, pad);
                bt[0] = (byte) (bt[0] ^ guess);

                byte[] copy = new byte[L];
                System.arraycopy(bytes, 0, copy, 0, L);
                System.arraycopy(bt, 0, copy, L - 17 - interestingByte, bt.length);

                String m = encoded_raw(copy);
                final String url = String.format("/po?er=%s", m);
                int status = helper.status(url, client.getTargetHost());
                if (status == 404) {
                    log.debug(String.format("(byte=%d) (guess=%d) = %d", interestingByte + 1, guess, status));
                    decoded[15 - interestingByte] = (byte) guess;
                    bytes[L - 17 - interestingByte] = (byte) guess;
                    log.debug(String.format("bytes=[%s]", Tools.encoded_raw(bytes)));
                    fail = false;
                    break;
                } else {
                    log.debug(String.format("incorrect guess=%d", guess));
                }
            }
        }
        System.out.println(Arrays.toString(decoded));
        System.out.println(printSpaces(decoded));
        System.out.println(printSpaces(bytes));
    }


}
