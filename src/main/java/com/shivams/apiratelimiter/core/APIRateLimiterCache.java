package com.shivams.apiratelimiter.core;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class APIRateLimiterCache {
    public static APIRateLimiterCache apiRateLimiterCache = null;

    private Integer numberOfTokens;

    private Map<String, Long> userLastRequestMapping;

    private Map<String, Integer> userTokens;

    public Integer getNumberOfTokens() {
        return numberOfTokens;
    }

    public void resetUserTokenAndFetch(String userId, Long timeStamp) {
        userLastRequestMapping.put(userId, timeStamp);
        userTokens.put(userId, numberOfTokens);
        fetchUserToken(userId);
    }

    public Integer fetchUserToken(String userId) {
        if(userTokens.containsKey(userId) && userTokens.get(userId) != 0) {
            userTokens.put(userId, userTokens.get(userId) - 1);
            return 1;
        }
        return 0;
    }

    public static APIRateLimiterCache getInstance() {
        if(apiRateLimiterCache == null) {
            apiRateLimiterCache = new APIRateLimiterCache();
        }
        return apiRateLimiterCache;
    }

    public void initialize(Integer numberOfTokens) {
        userLastRequestMapping = new HashMap<>();
        userTokens = new HashMap<>();
        this.numberOfTokens = numberOfTokens;
    }

    public Long checkUserLastRequestTime(String userId) {
        if(userLastRequestMapping.containsKey(userId)) {
            return userLastRequestMapping.get(userId);
        } else {
            userLastRequestMapping.put(userId, System.currentTimeMillis());
        }
        return null;
    }
}
