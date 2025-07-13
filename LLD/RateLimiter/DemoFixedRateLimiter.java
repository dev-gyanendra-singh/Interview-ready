package LLD.RateLimiter;

public class DemoFixedRateLimiter {

    public static void main(String[] args) throws InterruptedException {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(5, 10_000); // 5 requests per 10 seconds
        String userId = "user123";

        for (int i = 0; i < 20; i++) {
            boolean allowed = limiter.allowRequest(userId);
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(1000);
        }
    }
}
