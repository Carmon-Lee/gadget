package ratelimit.impl;

import ratelimit.RateLimiter;

public class LeakBucketLimiter implements RateLimiter {
    @Override
    public boolean request(String key) {
        return false;
    }
}
