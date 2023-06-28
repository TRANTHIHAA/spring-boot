package com.example.untiled1.global.resttemplate;

import com.example.untiled1.global.base.BasePageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

public interface IRestTemplateCustomizer {
    <T> T post(String url, Object request, Class<T> responseType);

    <T> T post(HttpHeaders httpHeaders, MultiValueMap<String, Object> map, String url, Class<T> responseType);

    <T> T post(String url, Object request, String token, Class<T> responseType);

    <T> Optional<T> get(String url, String token, String cApiKey, Class<T> responseType);

    <T> Optional<T> get(String url, Class<T> responseType);

    <T> List<T> getAll(String url, String token, String cApiKey);

    <T> Page<T> getPage(String url, String token, String cApiKey);

    UriComponentsBuilder toUriComponents(String url, Object obj, boolean isGetJsonProperty);

    <T> T postForObject (String url, Object imageUrls, Object parameters, String template, Class<T> responseType);

    <T> BasePageResponse<T> getForPageCustom(String url, String token, String cApiKey, Class<T> tClass);

    <T> BasePageResponse<T> getIamPagination(String url, String token, String cApiKey, Class<T> tClass);
}
