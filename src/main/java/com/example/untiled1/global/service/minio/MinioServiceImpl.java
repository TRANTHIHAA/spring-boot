//package com.example.untiled1.global.service.minio;
//
//import com.example.untiled1.global.service.MessageService;
//import io.minio.*;
//import io.minio.MinioClient;
//import io.minio.http.Method;
//import io.minio.messages.Item;
//import com.example.untiled1.global.dto.MinioItemDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.compress.harmony.archive.internal.nls.Messages;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class MinioServiceImpl implements IMinioService {
//    private final MinioClient minioClient;
//    private final MessageService messageService;
//    @Value("${config.storage.bucket.evisa-bucket}")
//    String bucketName;
//
//    @Override
//    public boolean put(String bucketName, String fileName, InputStream inputStream, long size, boolean isRequire) {
//        try {
//            if (!this.isExistedBucket(bucketName)) {
//                this.createBucket(bucketName);
//            }
//            return minioClient.putObject(PutObjectArgs.builder()
//                    .bucket(bucketName)
//                    .object(fileName)
//                    .stream(inputStream, size, -1)
//                    .build()) != null;
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            if (isRequire) {
//                throw new RuntimeException(this.messageService.getMessage(Messages.getString("upload file to MinIO failed")));
//            }
//            return false;
//        }
//    }
//
//    @Override
//    public InputStream get(String bucketName, String fileName, boolean isRequire) {
//        try {
//            if (fileName == null) {
//                return null;
//            }
//            if (this.isExistedBucket(bucketName)) {
//                return minioClient.getObject(GetObjectArgs.builder()
//                        .bucket(bucketName)
//                        .object(fileName)
//                        .build());
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            if (isRequire) {
//                throw new RuntimeException(this.messageService.getMessage(Messages.getString("download file from MinIO failed")));
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean delete(String bucketName, String fileName, boolean isRequire) {
//        try {
//            if (!this.isExistedBucket(bucketName)) {
//                this.createBucket(bucketName);
//            }
//            minioClient.removeObject(RemoveObjectArgs.builder()
//                    .bucket(bucketName)
//                    .object(fileName)
//                    .build());
//            return true;
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            if (isRequire) {
//                throw new RuntimeException(this.messageService.getMessage(Messages.getString("delete file from MinIO failed")));
//            }
//            return false;
//        }
//    }
//
//    @Override
//    public List<MinioItemDto> search(String bucketName, String searchText, boolean isRequire) {
//        try {
//            if (this.isExistedBucket(bucketName)) {
//                List<MinioItemDto> minioItems = new ArrayList<>();
//                Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
//                        .bucket(bucketName)
//                        .recursive(true)
//                        .prefix(searchText)
//                        .build());
//                if (results == null) {
//                    return new ArrayList<>();
//                }
//
//                for (Result<Item> result : results) {
//                    if (result == null) {
//                        continue;
//                    }
//                    minioItems.add(MinioItemDto.builder()
//                            .objectName(result.get().objectName())
//                            .size(result.get().size())
//                            .build());
//                }
//
//                return minioItems;
//            }
//        } catch (Exception ex) {
//            log.error(ex.getLocalizedMessage());
//            if (isRequire) {
//                throw new RuntimeException(this.messageService.getMessage(Messages.getString("list file from MinIO failed")));
//            }
//        }
//        return new ArrayList<>();
//    }
//
//    private boolean isExistedBucket(String bucket) {
//        try {
//            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
//        } catch (Exception ex) {
//            log.error(ex.getLocalizedMessage());
//            return false;
//        }
//    }
//
//    private void createBucket(String bucket) {
//        try {
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw new RuntimeException(this.messageService.getMessage(Messages.getString("lỗi hệ thống ")));
//        }
//    }
//
//    public String getLinkByObjectName(String objectName) {
//        try {
//            if (isExistedObject(objectName)) {
//                return this.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
//                        .method(Method.GET)
//                        .bucket(this.bucketName)
//                        .object(objectName)
//                        .build());
//            }
//            return null;
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            throw new RuntimeException(this.messageService.getMessage(Messages.getString("lỗi hệ thống")));
//        }
//    }
//
//    public boolean isExistedObject(String name) {
//        try {
//            return minioClient.statObject(StatObjectArgs.builder()
//                    .bucket(this.bucketName)
//                    .object(name).build()) != null;
//        } catch (Exception ex) {
//            log.error(ex.getLocalizedMessage());
//            return false;
//        }
//    }
//}
