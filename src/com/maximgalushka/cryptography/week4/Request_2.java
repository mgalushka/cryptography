package com.maximgalushka.cryptography.week4;

import com.maximgalushka.http.HttpHelper;
import com.maximgalushka.http.NoAuthSessionClient;
import org.apache.log4j.Logger;

import static com.maximgalushka.cryptography.Tools.bytes;
import static com.maximgalushka.cryptography.Tools.encoded_raw;

/**
 * @since 7/28/2014.
 */
public class Request_2 {

    private static final Logger log = Logger.getLogger(Request_2.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        NoAuthSessionClient client = new NoAuthSessionClient("crypto-class.appspot.com", 80, "http");
        HttpHelper helper = new HttpHelper<String>(client.getNewSessionClient());
        String M = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4";
        byte[] bytes = bytes(M);
        int L = bytes.length;

        // set correct byte
        bytes[L - 17] = 0x09;
        bytes[L - 18] = (byte) 195;

        for (int guess = 0; guess <= 255; guess++) {
            byte[] copy = new byte[L];
            System.arraycopy(bytes, 0, copy, 0, L);

            copy[L - 19] = (byte) (bytes[L - 19] ^ guess ^ (byte) 0x03);
            copy[L - 18] = (byte) (bytes[L - 18] ^ guess ^ (byte) 0x03);
            copy[L - 17] = (byte) (bytes[L - 17] ^ guess ^ (byte) 0x03);

            String m = encoded_raw(copy);
            final String url = String.format("/po?er=%s", m);
            int status = helper.status(url, client.getTargetHost());
            if (status == 404) {
                log.debug(String.format("status (guess=%d) = %d", guess, status));
                break;
            } else {
                log.debug(String.format("incorrect guess = %d", guess));
            }
        }
    }
}
