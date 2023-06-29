package com.example.untiled1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.opencv.core.Core;

@Configuration
public class OpenCVConfig {

    @Bean
    public void initOpenCV() {
        // Khởi tạo OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
