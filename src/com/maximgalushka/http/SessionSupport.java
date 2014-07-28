package com.maximgalushka.http;

import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * <p>Abstract session support for all sites</p>
 *
 * @author Maxim Galushka
 * @since 07/09/2011
 */
public abstract class SessionSupport {

    private static final com.maximgalushka.http.PropertiesHelper propertiesHelper = com.maximgalushka.http.PropertiesHelper.getHelper();

    private boolean requiredProxy;

    // Abstract session ID (PHP or else)
    private String sessionId;

    protected HttpHost targetHost;

    protected SessionSupport() {
        requiredProxy = Boolean.parseBoolean(propertiesHelper.getProperty("proxy.required"));
    }

    protected SessionSupport(HttpHost targetHost) {
        this.targetHost = targetHost;
    }

    protected String getSessionId() {
        return sessionId;
    }

    protected void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    protected DefaultHttpClient buildProxyClient() throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        if (requiredProxy) {
            HttpHost proxy = new HttpHost(propertiesHelper.getProperty("proxy.host"),
                    Integer.parseInt(propertiesHelper.getProperty("proxy.port")));
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }
        return httpClient;
    }

    protected abstract void buildBasicCookies(DefaultHttpClient httpClient);

    protected abstract DefaultHttpClient buildSessionClient(DefaultHttpClient client) throws IOException;

    /**
     * @return each call will return new session client linked to new HTTP session
     * @throws IOException is any
     */
    public DefaultHttpClient getNewSessionClient() throws IOException {
        DefaultHttpClient httpclient = buildProxyClient();
        return buildSessionClient(httpclient);
    }

    public DefaultHttpClient relogin() throws IOException {
        setSessionId(null);
        return getNewSessionClient();
    }

    public HttpHost getTargetHost() {
        return targetHost;
    }
}
