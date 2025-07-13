package LLD.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter {
    private final int maxRequests;
    private final long windowSizeMillis;
    private final ConcurrentHashMap<String, Window> userWindows = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        Window window = userWindows.computeIfAbsent(userId, k -> new Window(currentTime));

        synchronized (window) {
            if (currentTime - window.windowStart >= windowSizeMillis) {
                window.windowStart = currentTime;
                window.requestCount.set(0);
            }

            if (window.requestCount.incrementAndGet() <= maxRequests) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static class Window {
        long windowStart;
        AtomicInteger requestCount;

        Window(long start) {
            this.windowStart = start;
            this.requestCount = new AtomicInteger(0);
        }
    }
}
