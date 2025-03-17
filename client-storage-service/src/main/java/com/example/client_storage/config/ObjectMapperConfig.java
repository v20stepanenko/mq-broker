package com.example.client_storage.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.fasterxml.jackson.core.JsonParser.Feature.STRICT_DUPLICATE_DETECTION;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder
                                  .featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                                  .featuresToEnable(STRICT_DUPLICATE_DETECTION)
                                  .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                                  .featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                                  .featuresToEnable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                                  .modules(new JavaTimeModule());
    }
}
