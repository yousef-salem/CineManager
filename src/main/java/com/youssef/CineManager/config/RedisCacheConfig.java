package com.youssef.CineManager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setPassword(redisPassword);

        return new LettuceConnectionFactory(config);
    }

//    //     RedisTemplate Bean configuration for JSON serialization
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        // Jackson2JsonRedisSerializer for JSON serialization
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
// //        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
//
//        // Set the serializer for values
//        redisTemplate.setDefaultSerializer(serializer);
//
//        // Set key serializer to StringRedisSerializer (keys are typically simple strings)
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//
//        return redisTemplate;
//    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // Use JdkSerializationRedisSerializer for binary serialization
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // Keep keys as Strings

        return redisTemplate;
    }


    @Bean
    public CacheManager cacheManager() {

        RedisCacheConfiguration noTtlConfig = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("CineManager::"); // No TTL


        RedisCacheConfiguration ttlConfig = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("CineManager::").entryTtl(Duration.ofHours(24)); // TTL of 24 hours


        return RedisCacheManager.builder(redisConnectionFactory())
                .withCacheConfiguration("DynamicTTL", noTtlConfig)  // Cache without TTL
                .withCacheConfiguration("TTL24H", ttlConfig)  // Cache with TTL 24 hours
                .build();
    }
}