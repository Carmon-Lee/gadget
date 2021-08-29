/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package http;

import http.config.HttpAsyncClientInitBean;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liguang
 * @version HttpClientDemo.java, v 0.1 2020年10月21日 14:52
 */
public class HttpClientDemo {

    public static void main(String[] args) throws Exception {
        System.out.println(getTubeShareJumpKwaiLink("start to broadcaset", "111", "222", 1));
    }

    public static String getTubeShareJumpKwaiLink(String tips, String photoId, String tubeId, int episodeNumber)
            throws UnsupportedEncodingException {



        String newtips = "start to broadcast";


        String findFriendsWatchTubeInnerJumpUrl = "kwailive://startpush?livetype=3&needdelaystart=1&tips=%s"
                + "&theaterdatasourcedetail={\"photoId\":\"%s\",\"tubeId\":\"%s\",\"episodeNumber\":%d,"
                + "\"type\":1}&theatercontrol={\"switchportraitfullscreen\":1,\"openmicdialog\":1}";
        String findFriendsWatchTubeJumpUrl = "kwai://post?livesource=SHARE_FRIENDS_TOGETHER&tab=live&live_on=true"
                + "&livesubtype=voiceparty&voicepartytype=theater&innerjumpurls%5B%5D=";
        String shareUrlH5 = "kwaishare://h5?url=%s";
        String innerJumpLink =
                String.format(findFriendsWatchTubeInnerJumpUrl, tips, photoId, tubeId, episodeNumber);
        String encodedInnerJumpLink = URLEncoder.encode(innerJumpLink,
                StandardCharsets.UTF_8.toString());
        encodedInnerJumpLink = encodedInnerJumpLink.replaceAll("\\+", "%20");

        String watchTubeUrl = URLEncoder.encode(findFriendsWatchTubeJumpUrl + encodedInnerJumpLink,
                StandardCharsets.UTF_8.toString());

        return String.format(shareUrlH5, watchTubeUrl);
    }


    private static RequestConfig buildRequestConfig(boolean redirectEnable, int timeout) {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setCircularRedirectsAllowed(false)
                .setRedirectsEnabled(redirectEnable)
                .setSocketTimeout(timeout).build();
    }


    static class MyFutureCallback implements FutureCallback<HttpResponse> {

        private HttpContext httpContext;
        private ConcurrentMap<String, AtomicInteger> stat;

        public MyFutureCallback(HttpContext httpContext, ConcurrentMap<String, AtomicInteger> stat) {
            this.httpContext = httpContext;
            this.stat = stat;
        }

        @Override
        public void completed(HttpResponse result) {
            System.out.println("completed");
//            try {
//                System.out.println(EntityUtils.toString(result.getEntity()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            stat.get("complete").incrementAndGet();
        }

        @Override
        public void failed(Exception ex) {
            System.out.println("failed");
            stat.get("fail").incrementAndGet();
        }

        @Override
        public void cancelled() {
            System.out.println("cancelled");
            stat.get("cancel").incrementAndGet();
        }
    }


}