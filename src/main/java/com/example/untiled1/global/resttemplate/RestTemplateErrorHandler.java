package com.example.untiled1.global.resttemplate;

import com.example.untiled1.global.dto.ErrorResponseData;
import com.example.untiled1.global.service.MessageService;
import com.example.untiled1.global.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.harmony.archive.internal.nls.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {
    private final MessageService messageService;

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
//        return httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR ||
//                httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IOException(message(httpResponse));
        } else if (httpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
            throw new IOException("");
        } else if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new IOException(message(httpResponse));
        }
        throw new IOException(message(httpResponse));
    }

    private String message(ClientHttpResponse httpResponse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getBody()))) {
            String response = reader.lines().collect(Collectors.joining(""));
            ErrorResponseData data = getResponseError(response);
            if (data != null && data.getMessage() != null) {
                log.error(data.getMessage());
                return data.getMessage();
            }
            return httpResponse.getStatusText();
        } catch (IOException ex) {
            log.error(ex.getLocalizedMessage());
        }
        return this.messageService.getMessage(Messages.getString("error"));
    }

    private ErrorResponseData getResponseError(String response) {
        try {
            return JsonUtils.toClass(response, ErrorResponseData.class);
        } catch (Exception ex) {
            return null;
        }
    }
}
