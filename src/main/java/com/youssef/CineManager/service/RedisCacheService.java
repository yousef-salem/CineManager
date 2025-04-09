package com.youssef.CineManager.service;

public interface RedisCacheService {

    void put(String cacheName, String key, Object value);

    boolean exists(String cacheName, String key);

    void evict(String cacheName, String key);

    Object get(String cacheName, String key);
}
