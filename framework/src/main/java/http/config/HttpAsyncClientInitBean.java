/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package http.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.ssl.SSLContexts;

import java.util.concurrent.TimeUnit;

/**
 * 异步的httpClient bean
 *
 * @author xiemao
 * @version HttpAsyncClientInitBean.java, v 2.3.0 2020年04月17日 14:58
 */
@Slf4j
public class HttpAsyncClientInitBean extends AbstractHttpConfigBean {


    /**
     * 异步请求
     */
    private static CloseableHttpAsyncClient closeableHttpAsyncClient = null;

    private volatile ConnectionMonitor connectionMonitor;

    /**
     * 异步回调通知网关的域名
     */
    private static String callbackPrefix;

    public void setCallbackPrefix(String callbackPrefixUrl) {
        callbackPrefix = callbackPrefixUrl;
    }

    public void init() throws Exception {
        PoolingNHttpClientConnectionManager connectionManager = buildHttpClientConnectionManager();
        //初始化线程池清理的monitor
        if (connectionMonitor == null) {
            connectionMonitor = new ConnectionMonitor(connectionManager);
            connectionMonitor.start();
        }
        closeableHttpAsyncClient = HttpAsyncClients.custom()
                .setKeepAliveStrategy(new GateWayKeepAliveStrategy())
                .setConnectionManager(connectionManager)
                .setSSLHostnameVerifier(VERIFIER)
                .setDefaultRequestConfig(buildDefaultRequestConfig())
//                .setProxy(new HttpHost("localhost",8888))
                .build();
        closeableHttpAsyncClient.start();
    }

    public static CloseableHttpAsyncClient getCloseableHttpAsyncClient() {
        return closeableHttpAsyncClient;
    }

    private static PoolingNHttpClientConnectionManager buildHttpClientConnectionManager() throws IOReactorException {
        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().
                setIoThreadCount(2).
                setSoKeepAlive(true).
                build();
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(new DefaultConnectingIOReactor(ioReactorConfig), getRegistry());
        connManager.setDefaultMaxPerRoute(1);
        connManager.setMaxTotal(1);
        return connManager;
    }

    private static Registry<SchemeIOSessionStrategy> getRegistry() {
        return RegistryBuilder.<SchemeIOSessionStrategy>create()
                .register("http", NoopIOSessionStrategy.INSTANCE)
                .register("https", new SSLIOSessionStrategy(SSLContexts.createDefault(), getGatewayHostnameVerifier()))
                .build();
    }

    /**
     * 连接清理关闭manager
     */
    private static class ConnectionMonitor extends Thread {
        private final PoolingNHttpClientConnectionManager connectionManager;
        private volatile boolean shutdown;

        ConnectionMonitor(PoolingNHttpClientConnectionManager connMgr) {
            this.connectionManager = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(HTTP_CLIENT_CONNECTION_MANAGER_CLOSE_WAIT_TIME_MS);
                        connectionManager.closeExpiredConnections();
                        connectionManager.closeIdleConnections(HTTP_CLIENT_CONNECTION_MANAGER_CLOSE_IDLE_TIME_S, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                shutdown();
            }
        }

        void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public static String getCallbackPrefix() {
        return callbackPrefix;
    }

}
