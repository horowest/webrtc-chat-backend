package com.akash.util;

import com.akash.dto.RequestTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String stringyfy(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static RequestTO parse(String json) {
        try {
            return objectMapper.readValue(json, RequestTO.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
