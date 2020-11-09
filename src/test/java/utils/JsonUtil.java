package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static utils.JacksonObjectMapper.getMapper;

public class JsonUtil {
    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> List<T> readValuesToList(String json, Class<T> clazz) {
        ObjectReader objectReader = getMapper().readerFor(clazz);
        try {
            return objectReader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static Map<String, Integer> readValuesToMap(String json) {
        try {
            return getMapper().readValue(json, new TypeReference<TreeMap<String, Integer>>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read map from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}
