//package com.example.untiled1.global.service.minio;
//
//
//
//import com.example.untiled1.global.dto.MinioItemDto;
//
//import java.io.InputStream;
//import java.util.List;
//
//public interface IMinioService {
//    boolean put(String bucketName, String fileName, InputStream inputStream, long size, boolean isRequire);
//
//    InputStream get(String bucketName, String fileName, boolean isRequire);
//
//    boolean delete(String bucketName, String fileName, boolean isRequire);
//
//    List<MinioItemDto> search(String bucketName, String searchText, boolean isRequire);
//
//    String getLinkByObjectName(String name);
//
//    boolean isExistedObject(String name);
//}
