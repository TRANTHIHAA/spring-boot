//package com.example.untiled1.global.service.storage;
//
//import com.example.untiled1.global.service.MessageService;
//import com.example.untiled1.global.service.minio.IMinioService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.ContentDisposition;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//import java.util.Objects;
//
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class StorageService {
//    @Value("${config.storage.bucket.evisa-image-path}")
//    private String folderEvisa;
//
//    @Value("${config.storage.bucket.evisa-bucket}")
//    private String bucketEvisa;
//
//    @Value("${config.storage.bucket.evisa-signing-path}")
//    private String signPath;
//
//    private final IMinioService minioService;
//    private final MessageService messageService;
//
//    public InputStreamResource downloadFile(String fileName) throws IOException {
//        return new InputStreamResource(this.getFile(fileName));
//    }
//
//
//    private InputStream getFile(String fileName) throws IOException {
//        InputStream inputStream = minioService.get(bucketEvisa, fileName, false);
//        if (inputStream == null) {
//            throw new IOException(this.messageService.getMessage("re"));
//        }
//        return inputStream;
//    }
//
//    public String getLinkFile(String fileName) {
//        return minioService.getLinkByObjectName(fileName);
//    }
//
//
//    public HttpHeaders getHeaders(String fileName) {
//        HttpHeaders header = new HttpHeaders();
//        header.setContentDisposition(ContentDisposition
//                .attachment()
//                .filename(URLEncoder.encode(Objects.requireNonNull(fileName), StandardCharsets.UTF_8))
//                .build());
//        return header;
//    }
//}
