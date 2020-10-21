/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package http.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.HostnameVerifier;
import java.util.Map;

/**
 * @author xiemao
 * @version AbstractHttpConfigBean.java, v 2.3.0 2020年04月30日 14:51
 */
@Slf4j
public abstract class AbstractHttpConfigBean  {


    /**
     * 回收线程池等待时间
     */
    static final int HTTP_CLIENT_CONNECTION_MANAGER_CLOSE_WAIT_TIME_MS = 1000;

    /**
     * 闲置线程回收
     */
    static final int HTTP_CLIENT_CONNECTION_MANAGER_CLOSE_IDLE_TIME_S = 60;

    /**
     * 缓存SSL证书校验的结果
     */
    private static Map<String, Boolean> sslVerifierMap = Maps.newHashMap();

    /**
     * SSL证书本地缓存最大数量
     */
    private static final Integer MAX_SSL_VERIFIER_MAP_SIZE = 1000;

    /**
     * 默认的长连接策略里面是头部信息Keep-Alive参数中的timeout来进行设置的
     * 对于非标准的http连接或者头部信息中不存在，默认的策略设置成了-1
     */
    private static final long HTTP_CONNECT_FOREVER = -1;

    /**
     * 对于这种长连接主动释放，便于下次连接的建立
     */
    private static final long HTTP_KEEP_ALIVE_MIN_TIME_MS = 5000;

    private static final long HTTP_KEEP_ALIVE_MAX_TIME_MS = 30000;

    /**
     * 长连接提前释放时间
     */
    private static final long HTTP_KEEP_ALIVE_LONG_BEFORE_RELEASE_TIME_MS = 2000;

    private static final long HTTP_KEEP_ALIVE_SHORT_BEFORE_RELEASE_TIME_MS = 500;

    private static final int DEFAULT_CONNECT_TIMEOUT = 10000;

    public static final int DEFAULT_SOCKET_TIMEOUT = 30000;

    static final DefaultHostnameVerifier VERIFIER = new DefaultHostnameVerifier();

    /**
     * 配置默认的socket连接信息
     *
     * @return SocketConfig
     */
    SocketConfig buildDefaultSocketConfig() {
        return SocketConfig.custom()
                .setSoKeepAlive(true)
                .setTcpNoDelay(true)
                .build();
    }


    /**
     * 设置默认的请求配置参数,如果请求中设置了则用请求的参数
     *
     * @return RequestConfig
     */
    RequestConfig buildDefaultRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                .build();
    }


    /**
     * SSL证书校验缓存
     */
    static HostnameVerifier getGatewayHostnameVerifier() {
        return (s, sslSession) -> {
            Boolean verify = sslVerifierMap.get(s);
            if (verify == null) {
                boolean result = VERIFIER.verify(s, sslSession);
                if (sslVerifierMap.size() <= MAX_SSL_VERIFIER_MAP_SIZE) {
                    sslVerifierMap.put(s, result);
                }
                return result;
            }
            return verify;
        };
    }

    /**
     * 长连接策略
     */
    class GateWayKeepAliveStrategy extends DefaultConnectionKeepAliveStrategy {
        @Override
        public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
            return 1000_000_000;
        }
    }


}
