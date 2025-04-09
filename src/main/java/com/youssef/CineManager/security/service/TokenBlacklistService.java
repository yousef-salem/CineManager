package com.youssef.CineManager.security.service;

import com.youssef.CineManager.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {

    private static final String TOKEN_KEY_PREFIX = "BlacklistedToken::";
    private static final String CACHE_NAME = "TTL24H";

    private final RedisCacheService redisCacheService;

    @Autowired
    public TokenBlacklistService(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    public void blacklistToken(String token) {
        String key = TOKEN_KEY_PREFIX + token;
        redisCacheService.put(CACHE_NAME, key, token);
    }

    public boolean isTokenBlacklisted(String token) {
        String key = TOKEN_KEY_PREFIX + token;
        return redisCacheService.exists(CACHE_NAME, key);
    }
}
