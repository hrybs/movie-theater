package com.jpmc.theater.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

/**
 * Class can be used to obtain shared thread safe Object mapper
 * Class is thread safe
 */
@UtilityClass
public final class ObjectMapperProvider {

    private final static ObjectMapper OBJECT_MAPPER = createObjectMapper();

    /**
     * Can default object mapper with JSR310 support
     *
     * @return object mapper
     */
    public static ObjectMapper getDefaultObjectMapper() {
        return OBJECT_MAPPER;
    }

    private static ObjectMapper createObjectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }

}
