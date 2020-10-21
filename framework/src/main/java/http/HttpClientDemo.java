/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package http;

import http.config.HttpAsyncClientInitBean;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liguang
 * @version HttpClientDemo.java, v 0.1 2020年10月21日 14:52
 */
public class HttpClientDemo {

    public static void main(String[] args) throws InterruptedException {
        HttpGet httpGet =new HttpGet("http://localhost:11111/sleep/second");
        httpGet.setConfig(buildRequestConfig(true, 2000));
        ConcurrentMap<String, AtomicInteger> stat = new ConcurrentHashMap<>();
        stat.put("complete",new AtomicInteger());
        stat.put("fail",new AtomicInteger());
        stat.put("cancel",new AtomicInteger());

        HttpAsyncClientInitBean bean = new HttpAsyncClientInitBean();
        try {
            bean.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FutureCallback<HttpResponse> futureCallback = new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                System.out.println("completed");
                stat.get("complete").incrementAndGet();
            }

            @Override
            public void failed(Exception ex) {
                System.out.println(ex);
                System.out.println("failed");
                stat.get("fail").incrementAndGet();
            }

            @Override
            public void cancelled() {
                System.out.println("cancelled");
                stat.get("cancel").incrementAndGet();
            }
        };

        for (int i = 0; i < 100; i++) {
            HttpAsyncClientInitBean.getCloseableHttpAsyncClient().execute(httpGet, futureCallback);
        }

        TimeUnit.SECONDS.sleep(10);
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



}