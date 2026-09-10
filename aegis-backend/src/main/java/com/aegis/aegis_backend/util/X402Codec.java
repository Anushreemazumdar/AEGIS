package com.aegis.aegis_backend.util;



import tools.jackson.databind.json.JsonMapper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import tools.jackson.databind.ObjectMapper;

@org.springframework.stereotype.Component
public class X402Codec {

    private final ObjectMapper objectMapper;

    public X402Codec(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /** Turns a Java object into the Base64(JSON) string a header expects. */
    public String encode(Object payload) {
        String json = objectMapper.writeValueAsString(payload);
        return Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
    }

    /** Turns a header's Base64(JSON) string into a generic Map for inspection. */
    @SuppressWarnings("unchecked")
    public Map<String, Object> decode(String base64Header) {
        byte[] decoded = Base64.getDecoder().decode(base64Header);
        String json = new String(decoded, StandardCharsets.UTF_8);
        return objectMapper.readValue(json, Map.class);
    }
}
