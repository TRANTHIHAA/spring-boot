package com.example.untiled1.global.utils;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@Slf4j
public class FieldCasterUtils {
    private FieldCasterUtils() {
        throw new UnsupportedOperationException();
    }

    public static String toString(Object object) {
        try {
            return object.toString();
        } catch (Exception ex) {
            return null;
        }
    }

    public static Long toLong(Object object) {
        try {
            return Long.valueOf(object.toString());
        } catch (Exception ex) {
            return null;
        }
    }

    public static Integer toInteger(Object object) {
        try {
            return Integer.valueOf(object.toString());
        } catch (Exception ex) {
            return null;
        }
    }

    public static Double toDouble(Object object) {
        try {
            return Double.valueOf(object.toString());
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date toDate(Object object, @NotNull String... pattern) {
        try {
            return DateUtils.toDate(object.toString(), pattern);
        } catch (Exception ex) {
            return null;
        }
    }
}
