package com.example.untiled1.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class StorageConfig {
    @Value("${config.storage.url}")
    private String url;
    @Value("${config.storage.accessKey}")
    private String accessKey;
    @Value("${config.storage.secretKey}")
    private String secretKey;
    @Value("${config.storage.buckets.private}")
    private String privateBucket;
    @Value("${config.storage.buckets.public}")
    private String publicBucket;
}
