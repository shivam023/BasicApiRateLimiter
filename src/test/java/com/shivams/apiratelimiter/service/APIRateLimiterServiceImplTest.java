package com.shivams.apiratelimiter.service;

import com.shivams.apiratelimiter.core.APIRateLimiterCache;
import com.shivams.apiratelimiter.service.impl.APIRateLimiterServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class APIRateLimiterServiceImplTest {
    private APIRateLimiterCache apiRateLimiterCache;
    private APIRateLimiterService apiRateLimiterService;

    @Before
    public void setUp() {
        apiRateLimiterCache = APIRateLimiterCache.getInstance();
        apiRateLimiterService = new APIRateLimiterServiceImpl();
        apiRateLimiterCache.initialize(4);
    }

    @Test
    public void test_APIRateLimit_SameUser() throws InterruptedException {
        apiRateLimiterService.allowRequest("1");
        apiRateLimiterService.allowRequest("1");
        apiRateLimiterService.allowRequest("1");
        apiRateLimiterService.allowRequest("1");
        apiRateLimiterService.allowRequest("1");
        TimeUnit.MILLISECONDS.sleep(1000);
        Boolean flag = apiRateLimiterService.allowRequest("1");
        Assert.assertEquals(flag, Boolean.TRUE);
    }
}
