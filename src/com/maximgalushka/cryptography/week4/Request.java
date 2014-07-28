package com.maximgalushka.cryptography.week4;

import com.maximgalushka.http.HttpCallbackHandler;
import com.maximgalushka.http.HttpHelper;
import com.maximgalushka.http.NoAuthSessionClient;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @since 7/28/2014.
 */
public class Request {

    private static final Logger log = Logger.getLogger(Request.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        NoAuthSessionClient client = new NoAuthSessionClient("crypto-class.appspot.com", 80, "http");
        HttpHelper helper = new HttpHelper<String>(client.getNewSessionClient());

        final String url = String.format("/po?er=%s", "");
        int status = helper.status(url,
                client.getTargetHost(),
                new HttpCallbackHandler<String>() {
                    @Override
                    public String process(String content) throws Exception {
                        System.out.println(url);
                        return null;
                    }
                });
        log.debug(String.format("status = %d", status));
    }
}
