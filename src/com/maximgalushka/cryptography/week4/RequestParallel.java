package com.maximgalushka.cryptography.week4;

import com.maximgalushka.cryptography.Tools;
import com.maximgalushka.http.HttpHelper;
import com.maximgalushka.http.NoAuthSessionClient;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static com.maximgalushka.cryptography.Tools.*;

/**
 * @since 7/28/2014.
 */
public class RequestParallel {

    private static final Logger log = Logger.getLogger(Request_0.class);

    protected static Map<Integer, byte[]> pads() {
        Map<Integer, byte[]> pads = new HashMap<Integer, byte[]>((int) Math.ceil(64 * 0.75) + 1);
        for (int i = 1; i <= 64; i++) {
            byte[] bts = new byte[i];
            Arrays.fill(bts, (byte) i);
            pads.put(i, bts);
        }
        return pads;
    }

    public static class RequestTask implements Callable<Integer> {

        private HttpHelper helper;
        NoAuthSessionClient client;
        private String url;

        public RequestTask(HttpHelper helper, NoAuthSessionClient client, String url) {
            this.helper = helper;
            this.client = client;
            this.url = url;
        }

        @Override
        public Integer call() throws Exception {
            return helper.status(url, client.getTargetHost());
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        // The Magic Words are Squeamish Ossifrage
        String M = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4";
        String M1 = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0";
        String M2 = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd";
        String M3 = "58b1ffb4210a580f748b4ac714c001bdf20bdba6ff29eed7b046d1df9fb70000";
        byte[] full_bytes = bytes(M3);

        log.debug(Tools.encoded0(full_bytes));

        byte[] bytes = new byte[full_bytes.length];
        System.arraycopy(full_bytes, 0, bytes, 0, full_bytes.length);

        log.debug(Tools.encoded0(bytes));

        int L = bytes.length;
        Map<Integer, byte[]> pads = pads();
        byte[] decoded0 = new byte[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0x09, 0x09, 0x09, 0x09, 0x09, 0x09, 0x09, 0x09, 0x09
        };

        byte[] decoded = new byte[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        };
        boolean fail = false;

        ExecutorService es = Executors.newFixedThreadPool(4);

        for (int interestingByte = 0; interestingByte < 16; interestingByte++) {
            if (fail) {
                log.error("Incorrect!");
                break;
            }
            fail = true;
            log.debug(String.format("Started analysing [%d] byte", interestingByte + 1));
            byte[] pad = pads.get(interestingByte + 1);

            HashMap<Integer, Future<Integer>> tasks = new HashMap<Integer, Future<Integer>>(265);

            for (int guess = 0; guess <= 255; guess++) {
                byte[] bt = Arrays.copyOfRange(bytes, L - 17 - interestingByte, L - 16);
                if (bt.length != interestingByte + 1) throw new RuntimeException("Incorrect size!");
                if (bt.length != pad.length) throw new RuntimeException("Incorrect size!");
                bt = xor(bt, pad);

                byte[] encoded_part = Arrays.copyOfRange(decoded, decoded.length - 1 - interestingByte, 64);
                if (bt.length != encoded_part.length) throw new RuntimeException("Incorrect size!");
                bt = xor(bt, encoded_part);

                bt[0] = (byte) (bt[0] ^ guess);

                byte[] copy = new byte[L];
                System.arraycopy(bytes, 0, copy, 0, L);
                System.arraycopy(bt, 0, copy, L - 17 - interestingByte, bt.length);

                String m = encoded_raw(copy);

                NoAuthSessionClient client = new NoAuthSessionClient("crypto-class.appspot.com", 80, "http");
                HttpHelper helper = new HttpHelper<String>(client.getNewSessionClient());
                final String url = String.format("/po?er=%s", m);
                RequestTask rt = new RequestTask(helper, client, url);
                tasks.put(guess, es.submit(rt));
            }

            for (Integer guess : tasks.keySet()) {
                int status = tasks.get(guess).get();
                if (status == 404) {
                    log.debug(String.format("(byte=%d) (guess=%d: %c) = %d", interestingByte + 1, guess, Character.toChars(guess)[0], status));
                    byte guessBt = guess.byteValue();
                    decoded[decoded.length - 1 - interestingByte] = guessBt;
                    log.debug(String.format("decoded=[%s]", Tools.encoded_raw(decoded)));
                    log.debug(String.format("decoded=[%s]", Tools.printSpaces(decoded)));
                    fail = false;
                    break;
                } else {
                    log.trace(String.format("incorrect guess=%d", guess));
                }
            }
        }
        if (fail) {
            log.error(String.format("FAIL"));
        }
        log.debug(Arrays.toString(decoded));
        log.debug(printSpaces(decoded));
        log.debug(printSpaces(bytes));

        es.shutdownNow();
        es.awaitTermination(1, TimeUnit.SECONDS);
    }
}
