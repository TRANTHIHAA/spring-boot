package com.example.untiled1.global.service;

import com.example.untiled1.global.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.NotNull;
import java.util.Locale;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageSource messageSource;
    private final HttpServletRequest httpServletRequest;

    public static final String LOCALE_DEFAULT = "vi_VN";

    public String getMessage(@NotNull String text) {
        Locale locale = new Locale(LOCALE_DEFAULT);
        if (httpServletRequest != null) {
            String language = httpServletRequest.getHeader("Accept-Language");
            if (!StringUtils.isNullOrBlank(language)) {
                locale = new Locale(language);
            }
        }
        try {
            return this.messageSource.getMessage(text, null, locale);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return text;
        }
    }

    public String getMessage(String text, @NotNull Locale locale) {
        try {
            return this.messageSource.getMessage(text, null, locale);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return text;
        }
    }
}
