package ratelimit;

public class AbstractRateLimiter implements RateLimiter {

    private volatile int limit;

    public AbstractRateLimiter(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public boolean request(String key) {
        return false;
    }
}
