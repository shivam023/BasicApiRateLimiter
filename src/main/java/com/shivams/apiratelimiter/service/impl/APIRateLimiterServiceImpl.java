package com.shivams.apiratelimiter.service.impl;

import com.shivams.apiratelimiter.core.APIRateLimiterCache;
import com.shivams.apiratelimiter.service.APIRateLimiterService;

import java.util.Date;

public class APIRateLimiterServiceImpl implements APIRateLimiterService {

    APIRateLimiterCache apiRateLimiterCache = APIRateLimiterCache.getInstance();

    @Override
    public Boolean allowRequest(String userId) {
        Long currentTimeStamp = System.currentTimeMillis();
        Long lastRequestTimeStamp = apiRateLimiterCache.checkUserLastRequestTime(userId);
        if(lastRequestTimeStamp == null || currentTimeStamp - lastRequestTimeStamp > 1000) {
            apiRateLimiterCache.resetUserTokenAndFetch(userId, currentTimeStamp);
            return true;
        } else if(currentTimeStamp - lastRequestTimeStamp <= 1000) {
            Integer token = apiRateLimiterCache.fetchUserToken(userId);
            if(token == 1) return true;
        }
        return false;
    }
}
