package com.example.client_info.config;

import com.example.client.wiremock.model.FullNameResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static java.time.Duration.ofDays;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
public class RedisConfig {

    private String CACHE_NAME="full_name_cache";

    @Bean
    public RedisCacheManagerBuilderCustomizer redisBuilderCustomizer(ObjectMapper objectMapper) {
        ObjectMapper cacheObjectMapper = objectMapper.copy();
        cacheObjectMapper.activateDefaultTyping(cacheObjectMapper.getPolymorphicTypeValidator(),
                                                ObjectMapper.DefaultTyping.NON_FINAL,
                                                PROPERTY);
        GenericJackson2JsonRedisSerializer defaultSerializer = new GenericJackson2JsonRedisSerializer(cacheObjectMapper);

        Jackson2JsonRedisSerializer<FullNameResponse> fullNameJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(cacheObjectMapper, FullNameResponse.class);

        return builder -> builder.cacheDefaults(defaultCacheConfig()
                                                        .serializeValuesWith(fromSerializer(defaultSerializer))
                                                        .entryTtl(ofDays(1)))
                                  .withCacheConfiguration(CACHE_NAME, defaultCacheConfig()
                                                                              .serializeValuesWith(fromSerializer(fullNameJackson2JsonRedisSerializer))
                                                                              .entryTtl(ofDays(2)));
    }
}
