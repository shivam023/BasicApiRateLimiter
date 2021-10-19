package com.shivams.apiratelimiter.service;

public interface APIRateLimiterService {
    Boolean allowRequest(String userId);
}
