package com.shivams.apiratelimiter;

import com.shivams.apiratelimiter.core.APIRateLimiterCache;

public class APIRateLimiterApplication {
    public static void main(String args[]) {
        APIRateLimiterCache apiRateLimiterCache = APIRateLimiterCache.getInstance();
        apiRateLimiterCache.initialize(4);
    }
}
