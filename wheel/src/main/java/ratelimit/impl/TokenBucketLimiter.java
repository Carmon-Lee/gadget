package ratelimit.impl;

import ratelimit.AbstractRateLimiter;
import ratelimit.RateLimiter;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucketLimiter extends AbstractRateLimiter {

    private AtomicInteger counter;

    private ScheduledThreadPoolExecutor fillService;

    public TokenBucketLimiter(int limit) {
        super(limit);
        counter = new AtomicInteger();
        fillService = new ScheduledThreadPoolExecutor(1);
        init();
    }

    private void init() {
        fillService.scheduleAtFixedRate(() -> {
            if (counter.get() < getLimit()) {
                counter.incrementAndGet();
            }
        }, 0, 1000_000 / getLimit(), TimeUnit.MICROSECONDS);
    }

    @Override
    public boolean request(String key) {
        if (counter.get() <= 0) {
            return false;
        }
        return counter.getAndDecrement() > 0;
    }


    public static void main(String[] args) {
        RateLimiter limiter = new TokenBucketLimiter(10);
        int success = 0;
        for (int i = 0; i < 1000; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (limiter.request("")) {
                success++;
            }
        }
        System.out.println(success);
    }
}
