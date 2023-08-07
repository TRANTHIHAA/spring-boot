package com.example.untiled1.global.base;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class BaseMessages {

    private final MessageSource messageSource;

    public static final String LOCALE_DEFAULT = "vi";

    public String getMessage(String key, Locale locale) {
        if (locale == null) {
            locale = new Locale(LOCALE_DEFAULT);
        }

        try {
            return this.messageSource.getMessage(key, null, locale);
        } catch (NoSuchMessageException var4) {
            return key;
        }
    }

    public String getMessage(String key) {
        return this.getMessage(key, LocaleContextHolder.getLocale());
    }

    public static class Tutorial {
        private Tutorial() {

        }

        public static final String HEADER_REPORT_EXCEL = "Mã, Mô Tả, Tiêu Đề, Trạng thái";
//        public static final String HEADER_REPORT_EXCEL = "default.tutorial.header";
//        public static final String SHEET_NAME = "default.tutorial.sheetname";
        public static final String SHEET_NAME = "Tutorial";
        public static final String FILE_NAME = "Tutorial";
//        public static final String FILE_NAME = "default.tutorial.filename";
    }

    public static class QuocGia {
        private QuocGia() {
        }

        public static final String HEADER_REPORT_EXCEL = "default.quocgia.header";
        public static final String SHEET_NAME = "default.quocgia.sheetname";
        public static final String FILE_NAME = "default.quocgia.filename";
    }

}
