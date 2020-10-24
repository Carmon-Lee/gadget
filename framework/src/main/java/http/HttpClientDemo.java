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
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liguang
 * @version HttpClientDemo.java, v 0.1 2020年10月21日 14:52
 */
public class HttpClientDemo {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentMap<String, AtomicInteger> stat = new ConcurrentHashMap<>();
        stat.put("complete", new AtomicInteger());
        stat.put("fail", new AtomicInteger());
        stat.put("cancel", new AtomicInteger());

        HttpAsyncClientInitBean bean = new HttpAsyncClientInitBean();
        try {
            bean.init();
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 2; i++) {
            HttpGet httpGet = new HttpGet("http://172.16.7.147:11111/sleep/second");
//        HttpGet httpGet =new HttpGet("http://local:11112/sleep/second");
            httpGet.setConfig(buildRequestConfig(true, 2000000));
            HttpContext httpContext = new BasicHttpContext();
            HttpAsyncClientInitBean.getCloseableHttpAsyncClient().execute(httpGet, httpContext, new MyFutureCallback(httpContext, stat));
        }

//        for (int i = 0; i < 1; i++) {
////            HttpGet httpGet = new HttpGet("http://172.16.7.147:11111/sleep/second");
//        HttpGet httpGet =new HttpGet("http://localhost:11111/sleep/second");
//            httpGet.setConfig(buildRequestConfig(true, 2000));
//            HttpContext httpContext = new BasicHttpContext();
//            HttpAsyncClientInitBean.getCloseableHttpAsyncClient().execute(httpGet, httpContext, new MyFutureCallback(httpContext, stat));
//        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(stat);
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