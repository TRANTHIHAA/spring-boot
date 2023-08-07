package com.example.untiled1.global.utils;

import com.example.untiled1.domain.auth.dto.LoginResponseDto;

import com.google.gson.Gson;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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
    public static final String DOT = ".";
    public static final String ONE_SPACE = " ";
    public static final String ACTIVE = "Kích hoạt";
    public static final String INACTIVE = "Không kích hoạt";
    public static final String TAIL_XLSX = ".xlsx";

    public static final String TAB_AFTER_TEXT = "</w:t><w:tab/><w:t>";

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

    public static String appendIfMissing(Object object, int total) {
        if (object == null || object.toString() == null) {
            return EMPTY;
        }
        StringBuilder addZero = new StringBuilder();
        if (object.toString().length() <= total) {
            addZero.append("0".repeat(Math.max(0, total - object.toString().length()))).append(object);
            return addZero.toString();
        }
        return object.toString();
    }

//    public static String appendIfMissing1(Object object) {
//        return appendIfMissing(object, "0", 2);
//    }

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
        return name.concat(tail.length > 0 ? tail[0] : FileUtils.TAIL_XLSX);
    }

    public static String trimObject(Object e) {
        if (e != null) {
            return e.toString().trim();
        }
        return null;
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public static String formatDate2(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static String formatDate3(Date date) {
        return new SimpleDateFormat("ddMMyyyy").format(date);
    }

    public static String formatTime2FileName(Date date) {
        return new SimpleDateFormat("dd_MM_yyyy").format(date);
    }

    public static String formatDate2FileName(Date date) {
        return new SimpleDateFormat("HH_mm_ss_SSSS").format(date);
    }

    public static String appendIfMissing(Object object) {
        return appendIfMissing(object, 2);
    }

    public static Object getColumnNotNullAndValueOf(Object o) {
        if (o == null || String.valueOf(o).length() < 1) {
            throw new NullPointerException();
        }
        return o;
    }

    public static boolean checkColumnNotNull(Object o) {
        return o == null || String.valueOf(o).length() < 1;
    }

    public static String concat3String(String ma1, String ma2, String ma3) {
        return String.format("%s%s%s", ma1, ma2, ma3);
    }

    public static <T> void assertValidObjects(List<T> objectList, String message) {
        if (objectList.isEmpty()) {
            throw new ResourceNotFoundException(message);
        }
    }


    public static <T> void assertValidObjectsIsNotNull(T object, String message) {
        if (object == null) {
            throw new ResourceNotFoundException(message);
        }
    }

    public static String removeAccent(String text) {
        String temp = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replace("đ", "d");
        temp = temp.replace("Đ", "D");
        return temp;
    }

    public static boolean isExistedInArray(Object object, Object[] array) {
        for (Object obj : array) {
            if (Objects.equals(object, obj)) {
                return true;
            }
        }
        return false;
    }

    public static String toString(Object object, String dot) {
        if (dot == null) {
            return toString(object);
        }
        if (object == null) {
            return dot;
        }
        if (object instanceof Date) {
            return formatDate((Date) object);
        }
        return String.valueOf(object);
    }
    public static String toString(Object object) {
        if (object == null) {
            return EMPTY;
        }
        if (object instanceof Date) {
            return formatDate((Date) object);
        }
        return String.valueOf(object);
    }

    public static String getFileName(String type, String code) {
        return type.concat("-").concat(code).concat(".pdf");
    }

    public static Long getExpFromToken(String token) {
        if (token == null) {
            return 0L;
        }
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        LoginResponseDto loginResponse = new Gson().fromJson(payload, LoginResponseDto.class);
        if (loginResponse == null) {
            return 0L;
        }
        return loginResponse.getExp();
    }

}
