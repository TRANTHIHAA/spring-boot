package com.example.untiled1.global.utils;

import com.google.gson.Gson;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@Slf4j
public class JsonUtils {
    private static Gson gSonMapper;
    private JsonUtils() {
        throw new UnsupportedOperationException();
    }

    // private static ObjectMapper mObjectMapper;

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
  //   public static boolean isValid(String json) {
  //   try {
  //     ObjectMapper mapper = new ObjectMapper();
  //     mapper.readTree(json);
  //   } catch (JacksonException e) {
  //     return false;
  //   }
  //   return true;
  // }

  //     public static <T> T toClass(Object o, Class<T> tClass) {
  //   try {
  //     if (Objects.equals(o.getClass().getName(), tClass.getName())) {
  //       return (T) o;
  //     }
  //     String tempJson;
  //     if (o instanceof String) {
  //       tempJson = o.toString();
  //     } else {
  //       tempJson = toJson(o);
  //     }
  //     return getGSonMapper().fromJson(tempJson, tClass);
  //   } catch (Exception e) {
  //     throw new InternalServerErrorException(e.getMessage());
  //   }
  // }

  //     private static Gson getGSonMapper() {
  //   if (gSonMapper == null) {
  //     gSonMapper = new Gson();
  //   }
  //   return gSonMapper;
  // }
}
