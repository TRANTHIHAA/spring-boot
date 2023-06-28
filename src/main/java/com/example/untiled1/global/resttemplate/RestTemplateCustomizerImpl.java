package com.example.untiled1.global.resttemplate;

import com.example.untiled1.global.base.BasePageResponse;
import com.example.untiled1.global.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.*;

@Component
@Slf4j
public class RestTemplateCustomizerImpl implements IRestTemplateCustomizer {
    private final RestTemplate restTemplate;

    public RestTemplateCustomizerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T post(String url, Object request, Class<T> responseType) {
        try {
            HttpEntity<?> httpEntity = new HttpEntity<>(request);
            return restTemplate.postForObject(url, httpEntity, responseType);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public <T> T post(HttpHeaders httpHeaders, MultiValueMap<String, Object> map, String url, Class<T> responseType) {
        try {
            HttpEntity<MultiValueMap<String, Object>> requestBodyFormUrlEncoded = new HttpEntity<>(map, httpHeaders);
            return restTemplate.postForObject(url, requestBodyFormUrlEncoded, responseType);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public <T> T post(String url, Object request, String token, Class<T> responseType) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            HttpEntity<?> httpEntity = new HttpEntity<>(request, httpHeaders);
            return restTemplate.postForObject(url, httpEntity, responseType);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public <T> Optional<T> get(String url, String token, String cApiKey, Class<T> responseType) {
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(URI.create(url), HttpMethod.GET, getHttpEntity(token, cApiKey), responseType);
            if (responseEntity.hasBody()) {
                return Optional.ofNullable(responseEntity.getBody());
            } else {
                return Optional.empty();
            }
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<T> get(String url, Class<T> responseType) {
        return this.get(url, null, null, responseType);
    }

    @Override
    public <T> List<T> getAll(String url, String token, String cApiKey) {
        ResponseEntity<List<T>> listResponseEntity = restTemplate.exchange(URI.create(url), HttpMethod.GET, getHttpEntity(token, cApiKey), new ParameterizedTypeReference<>() {
        });
        if (listResponseEntity.hasBody()) {
            return listResponseEntity.getBody();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public <T> Page<T> getPage(String url, String token, String cApiKey) {
        ResponseEntity<Page<T>> listResponseEntity = restTemplate.exchange(URI.create(url), HttpMethod.GET, getHttpEntity(token, cApiKey), new ParameterizedTypeReference<>() {
        });
        if (listResponseEntity.hasBody()) {
            return listResponseEntity.getBody();
        } else {
            return new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 0), 0);
        }
    }

    @Override
    public UriComponentsBuilder toUriComponents(String url, Object obj, boolean isGetJsonProperty) {
        UriComponentsBuilder uriComponents = UriComponentsBuilder.fromHttpUrl(url);
        Map<String, Object> params = this.objectToMapParams(obj, true, isGetJsonProperty);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            uriComponents.queryParam(entry.getKey(), entry.getValue());
        }
        return uriComponents;
    }

    @Override
    public <T> T postForObject(String url, Object imageUrls, Object parameters, String template, Class<T> responseType) {
        return null;
    }
//
//    @Override
//    public <T> T postForObject(String url, Object imageUrls, Object parameters, String template, Class<T> responseType) {
//        PdfReportRequest request = new PdfReportRequest();
//        request.setParameters(parameters);
//        request.setImageUrls(imageUrls);
//        request.setTemplate(template);
//        request.setType("pdf");
//        return this.post(url, request, responseType);
//    }

    public Map<String, Object> objectToMapParams(Object obj, boolean isAccessible, boolean isGetJsonProperty) {
        if (obj == null) {
            return Collections.emptyMap();
        } else {
            Map<String, Object> paramsMap = new HashMap<>();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(isAccessible);
                try {
                    if (field.get(obj) != null) {
                        paramsMap.put(isGetJsonProperty ? field.getAnnotation(JsonProperty.class).value() : field.getName(), field.get(obj));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return paramsMap;
        }
    }

    private <T> HttpEntity<T> getHttpEntity(String token, String cApiKey) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (token != null) {
            httpHeaders.set(HttpHeaders.AUTHORIZATION, token);
        }
        if (cApiKey != null) {
            httpHeaders.set("cApiKey", cApiKey);
        }
        return new HttpEntity<>(httpHeaders);
    }

    public <T> BasePageResponse<T> getForPageCustom(String url, String token, String cApiKey, Class<T> tClass) {
        ResponseEntity<BasePageResponse<T>> listResponseEntity = restTemplate.exchange(URI.create(url), HttpMethod.GET, getHttpEntity(token, cApiKey), new ParameterizedTypeReference<>() {
        });

        if (listResponseEntity.hasBody()) {
            BasePageResponse<T> response = listResponseEntity.getBody();
            if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
                List<T> list = new ArrayList<>();
                List<T> items = response.getItems();
                for (T t : items) {
                    T element = toClass(t, tClass);
                    if (element != null) {
                        list.add(element);
                    }
                }
                response.setItems(list);
            }
            return response;
        } else {
            return new BasePageResponse<>(new ArrayList<>());
        }
    }

    @Override
    public <T> BasePageResponse<T> getIamPagination(String url, String token, String cApiKey, Class<T> tClass) {
        return null;
    }

//    public <T> BasePageResponse<T> getIamPagination(String url, String token, String cApiKey, Class<T> tClass) {
//        ResponseEntity<IamPageResponse<T>> listResponseEntity = restTemplate.exchange(URI.create(url),
//                HttpMethod.GET,
//                getHttpEntity(token, cApiKey),
//                new ParameterizedTypeReference<>() {
//                });
//
//        BasePageResponse result = new BasePageResponse<>(new ArrayList<>());
//        if (listResponseEntity.hasBody()) {
//            IamPageResponse<T> response = listResponseEntity.getBody();
//            if (response != null && response.getResult() != null && !response.getResult().isEmpty()) {
//                List<T> list = new ArrayList<>();
//                List<T> items = response.getResult();
//                for (T t : items) {
//                    T element = JsonUtils.toClassFromLinkedHashMap(t, tClass);
//                    if (element != null) {
//                        list.add(element);
//                    }
//                }
//                result.setItems(list);
//                result.setTotal(response.getTotalElements());
//            }
//        }
//
//        return result;
//    }

    private <T> T toClass(T t, Class<T> tClass) {
        try {
            return JsonUtils.toClassSs(t, tClass);
        } catch (Exception ex) {
            return null;
        }
    }

}
