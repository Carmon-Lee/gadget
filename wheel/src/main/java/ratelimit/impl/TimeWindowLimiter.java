package ratelimit.impl;

import ratelimit.RateLimiter;

public class TimeWindowLimiter implements RateLimiter {

    @Override
    public boolean request(String key) {
        return false;
    }
}
