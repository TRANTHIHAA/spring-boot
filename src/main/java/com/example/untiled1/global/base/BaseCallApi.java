package com.example.untiled1.global.base;

import com.example.untiled1.global.resttemplate.IRestTemplateCustomizer;
import com.example.untiled1.global.utils.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Data
public class BaseCallApi<T> {
    @Value("${app.current-app.api-key}")
    private String cApiKey;
    @Value("${app.restapi.dmdc.headers.token}")
    private String token;

    private final Class<T> tClass;

    private final String baseUrl;

    private final String api;

    @Autowired
    private IRestTemplateCustomizer iRestTemplateCustomizer;

    public BaseCallApi(Class<T> tClass, String baseUrl, String api) {
        this.tClass = tClass;
        this.baseUrl = baseUrl;
        this.api = api;
    }

    public List<T> findAll() {
        return this.iRestTemplateCustomizer.getAll(getUrl(), this.token, this.cApiKey);
    }

    public List<T> findAll(Object searchRequest) {
        UriComponentsBuilder uriComponents = this.iRestTemplateCustomizer.toUriComponents(getUrl(), searchRequest, false);
        return this.iRestTemplateCustomizer.getAll(uriComponents.toUriString(), token, cApiKey);
    }

    public Page<T> getPage(Object searchRequest) {
        UriComponentsBuilder uriComponents = this.iRestTemplateCustomizer.toUriComponents(getUrl(), searchRequest, false);
        return this.iRestTemplateCustomizer.getPage(uriComponents.toUriString(), token, cApiKey);
    }

    public Optional<T> findByMa(String ma) {
        if (StringUtils.isNullOrBlank(ma)) {
            return Optional.empty();
        }
        return this.iRestTemplateCustomizer.get(getUrl().concat("/").concat(ma), token, cApiKey, tClass);
    }

    protected String getUrl() {
        return baseUrl.concat("/").concat(api);
    }

    public BasePageResponse<T> getPageCustom(Object searchRequest, Class<T> tClass) {
        UriComponentsBuilder uriComponents = this.iRestTemplateCustomizer.toUriComponents(getUrl(), searchRequest, false);
        return this.iRestTemplateCustomizer.getForPageCustom(uriComponents.toUriString(), token, cApiKey, tClass);
    }

    public BasePageResponse<T> getIamPagination(Object searchRequest, Class<T> tClass) {
        UriComponentsBuilder uriComponents = this.iRestTemplateCustomizer.toUriComponents(getUrl(), searchRequest, false);
        return this.iRestTemplateCustomizer.getIamPagination(uriComponents.toUriString(), token, cApiKey, tClass);
    }
}
