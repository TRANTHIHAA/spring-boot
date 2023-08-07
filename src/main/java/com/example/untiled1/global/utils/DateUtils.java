package com.example.untiled1.global.utils;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateUtils {
    private DateUtils() {
        throw new UnsupportedOperationException();
    }

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YY = "yy";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String YYYY_MM_DD_HH_MM_SS_1 = "yyyy_MM_dd_HH_mm_ss";
    public static final String YYYY_MM_DD_1 = "yyyy_MM_dd";
    public static final String YYYY_MM_DD_HH_MM_SS_3 = "yyyy/MM/dd_HH_mm_ss";
    public static final String YYYY_MM_DD_2 = "yyyyMMdd";
    public static final String DD_MM_YYYY_1 = "dd-MM-yyyy";
    public static final Long WEEK = 7L;
    public static final Long YEAR = 365L;

    public static String dateToString(Date date, String... format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format.length > 0 ? format[0] : YYYY_MM_DD_HH_MM_SS).format(date);
    }
    public static String toString(Date date, String... format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format.length > 0 ? format[0] : YYYY_MM_DD_HH_MM_SS).format(date);
    }

    public static Date toDate(String text, @NotNull String... format) {
        try {
            return new SimpleDateFormat(format.length > 0 ? format[0] : YYYY_MM_DD_HH_MM_SS).parse(text);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static boolean isDateAfter(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.getTime() > date2.getTime();
    }

    public static boolean isSameOrAfter(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.getTime() >= date2.getTime();
    }

    public static Date getStartOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static boolean compareTwoDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return getStartOfDate(date1) != getStartOfDate(date2);
    }

    public static boolean isDayDiff(Date date1, Date date2, Long period) {
        if (date1 == null || date2 == null || period == null) {
            return false;
        }
        return TimeUnit.MILLISECONDS.toDays(date1.getTime() - date2.getTime()) > period;
    }

}
