package com.example.untiled1.global.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Slf4j
public class JsonUtils {
    private static Gson gSonMapper;

    private static ObjectMapper mObjectMapper;

    private JsonUtils() {
        throw new UnsupportedOperationException();
    }

    public static <T> List<T> toClass(@NotNull File file, @NotNull Class<? extends T[]> tClass) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return Arrays.asList(new Gson().fromJson(bufferedReader, tClass));
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
        }
        return new ArrayList<>();
    }

    public static <T> T toClass(@NotEmpty String json, @NotNull Class<T> tClass) {
        try {
            return new Gson().fromJson(json, tClass);
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return null;
        }
    }

    public static <T> String toJson(@NotNull T t) {
        try {
            return new Gson().toJson(t);
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return null;
        }
    }

    public static <T> T toClassFromLinkedHashMap(Object o, Class<T> tClass) {
        String data = getGSonMapper().toJson(o, LinkedHashMap.class);
        return toClass(data, tClass);
    }

    public static <T> T toClassSs(Object o, Class<T> tClass) {
        try {
            if (Objects.equals(o.getClass().getName(), tClass.getName())) {
                return (T) o;
            }
            String tempJson;
            if (o instanceof String) {
                tempJson = o.toString();
            } else {
                tempJson = toJson(o);
            }
            return getGSonMapper().fromJson(tempJson, tClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Gson getGSonMapper() {
        if (gSonMapper == null) {
            gSonMapper = new Gson();
        }
        return gSonMapper;
    }

    public static <T> T entity(String json, Class<T> tClass) throws IOException {
        return getMapper().readValue(json, tClass);
    }

    private static ObjectMapper getMapper() {
        if (mObjectMapper == null) {
            mObjectMapper = new ObjectMapper();
        }
        return mObjectMapper;
    }

    public static <T> List<T> arrayList(String json, Class<T> tClass) {
        try {
            TypeFactory typeFactory = getMapper().getTypeFactory();
            JavaType type = typeFactory.constructCollectionType(ArrayList.class, tClass);
            return getMapper().readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
