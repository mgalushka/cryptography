package com.maximgalushka.http;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * <p>Lightweight session client used when no authentication required for retrieving data by HTTP</p>
 *
 * @author Maxim Galushka
 * @since 08/09/2011
 */
public class NoAuthSessionClient extends SessionSupport {

    private static final Logger log = Logger.getLogger(NoAuthSessionClient.class);

    public NoAuthSessionClient(String host, int port, String protocol) {
        this.targetHost = new HttpHost(host, port, protocol);
    }

    @Override
    protected void buildBasicCookies(DefaultHttpClient httpClient) {
        log.trace(String.format("Session creation is not required for %s site", getTargetHost()));
    }

    @Override
    protected DefaultHttpClient buildSessionClient(DefaultHttpClient client) throws IOException {
        log.trace(String.format("Session creation is not required for %s site", getTargetHost()));
        return client;
    }
}
