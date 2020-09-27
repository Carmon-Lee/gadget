package ratelimit;

public interface RateLimiter {

    boolean request(String key);
}
