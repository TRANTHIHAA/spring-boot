package com.example.untiled1.global.utils;

import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

import static com.example.untiled1.global.utils.FileUtils.TAIL_XLSX;

@Slf4j
public class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    public static final String EMPTY = "";
    public static final String PERCENT = "%";
    public static final String TAIL_PDF = ".pdf";
    public static final String TAIL_JSON = ".json";
    public static final String COMMA = ",";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public static boolean isNullOrBlank(String text) {
        return text == null || text.trim().isBlank();
    }

    public static String appendIfMissing(Object object, @NotEmpty String symbol, int size) {
        if (object == null || object.toString().isBlank()) {
            return null;
        }
        StringBuilder addZero = new StringBuilder();
        if (object.toString().length() <= size) {
            addZero.append(symbol.repeat(Math.max(0, size - object.toString().length()))).append(object);
            return addZero.toString();
        }
        return object.toString();
    }

    public static String appendIfMissing(Object object) {
        return appendIfMissing(object, "0", 2);
    }

    public static String ifNullToEmpty(Object text) {
        return text == null ? EMPTY : text.toString();
    }

    public static String toSearchQuery(String text, int mode, boolean isUpperCase) {
        text = ifNullToEmpty(text);
        text = (isUpperCase ? text.trim().toUpperCase(Locale.ROOT) : text.trim());
        if (mode == 0) {
            return PERCENT + text + PERCENT;
        } else if (mode == 1) {
            return text + PERCENT;
        } else {
            return PERCENT + text;
        }
    }

    public static String toFileName(String name, String... tail) {
        return name.concat(tail.length > 0 ? tail[0] : TAIL_XLSX);
    }
}
