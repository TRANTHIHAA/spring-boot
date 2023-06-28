package com.example.untiled1.global.storage;

import com.example.untiled1.configuration.StorageConfig;
import io.minio.*;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Storage {
    private final StorageConfig config;
    private final MinioClient client;

    public Storage(StorageConfig config) {
        this.config = config;
        this.client = MinioClient.builder()
                .endpoint(config.getUrl())
                .credentials(config.getAccessKey(), config.getSecretKey())
                .build();
    }

    public MinioClient getClient() {
        return client;
    }

    public List<Item> listPublicObjects() {
        return listObjects(config.getPublicBucket(), null, null);
    }

    public List<Item> listPrivateObjects() {
        return listObjects(config.getPrivateBucket(), null, null);
    }

    public List<Item> listPublicObjects(String path) {
        return listObjects(config.getPublicBucket(), path, null);
    }

    public List<Item> listPrivateObjects(String path) {
        return listObjects(config.getPrivateBucket(), path, null);
    }

    public List<Item> listPublicObjects(String path, Boolean recursive) {
        return listObjects(config.getPublicBucket(), path, recursive);
    }

    public List<Item> listPrivateObjects(String path, Boolean recursive) {
        return listObjects(config.getPrivateBucket(), path, recursive);
    }

    public List<Item> listPublicObjects(Boolean recursive) {
        return listObjects(config.getPublicBucket(), null, recursive);
    }

    public List<Item> listPrivateObjects(Boolean recursive) {
        return listObjects(config.getPrivateBucket(), null, recursive);
    }

    public InputStream getPublicObject(String path) {
        return getObject(config.getPublicBucket(), path);
    }

    public InputStream getPrivateObject(String path) {
        return getObject(config.getPrivateBucket(), path);
    }

    public StatObjectResponse getPublicObjectMetadata(String path) {
        return getMetadata(config.getPublicBucket(), path);
    }

    public StatObjectResponse getPrivateObjectMetadata(String path) {
        return getMetadata(config.getPrivateBucket(), path);
    }

    public void uploadToPrivateBucket(String path, InputStream file, String contentType, Map<String, String> headers) {
        upload(config.getPrivateBucket(), path, file, contentType, headers);
    }

    public void uploadToPublicBucket(String path, InputStream file, String contentType, Map<String, String> headers) {
        upload(config.getPublicBucket(), path, file, contentType, headers);
    }

    public void uploadToPrivateBucket(String path, InputStream file, String contentType) {
        upload(config.getPrivateBucket(), path, file, contentType, null);
    }

    public void uploadToPublicBucket(String path, InputStream file, String contentType) {
        upload(config.getPublicBucket(), path, file, contentType, null);
    }

    public void uploadToPrivateBucket(String path, InputStream file, Map<String, String> headers) {
        upload(config.getPrivateBucket(), path, file, null, headers);
    }

    public void uploadToPublicBucket(String path, InputStream file, Map<String, String> headers) {
        upload(config.getPublicBucket(), path, file, null, headers);
    }

    public void uploadToPrivateBucket(String path, InputStream file) {
        upload(config.getPrivateBucket(), path, file, null, null);
    }

    public void uploadToPublicBucket(String path, InputStream file) {
        upload(config.getPublicBucket(), path, file, null, null);
    }

    private void upload(String bucket, String path, InputStream file, String contentType, Map<String, String> headers) {
        try {
            var args = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .stream(file, file.available(), -1);
            if (contentType != null) {
                args.contentType(contentType);
            }
            if (headers != null) {
                args.headers(headers);
            }
            client.putObject(args.build());
        } catch (Exception e) {
            throw new RuntimeException("upload file không thành công", e);
        }
    }

//    public void deleteAll() throws Exception{
//
//        Iterable<Result<Item>> results = client.listBuckets(config.getPrivateBucket());
//        for (Result<Item> result : results) {
//            client.removeObject(config.getPrivateBucket(), result.get().objectName());
//        }
//    }


    private List<Item> getItems(Iterable<Result<Item>> myObjects) {
        return StreamSupport
                .stream(myObjects.spliterator(), true)
                .map(itemResult -> {
                    try {
                        return itemResult.get();
                    } catch (Exception e) {
                        throw new RuntimeException("Error while parsing list of objects");
                    }
                })
                .collect(Collectors.toList());
    }


    private InputStream getObject(String bucket, String path) {
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .build();
            return client.getObject(args);
        } catch (Exception e) {
            throw new RuntimeException("tải file không thành công", e);
        }
    }

    private StatObjectResponse getMetadata(String bucket, String path) {
        try {
            StatObjectArgs args = StatObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .build();
            return client.statObject(args);
        } catch (Exception e) {
            throw new RuntimeException("tải metadata không thành công", e);
        }
    }

    private List<Item> listObjects(String bucket, String prefix, Boolean recursive) {
        var args = ListObjectsArgs.builder()
                .bucket(bucket);
        if (prefix != null) {
            args.prefix(prefix);
        }
        if (recursive != null) {
            args.recursive(recursive);
        }
        Iterable<Result<Item>> objects = client.listObjects(args.build());
        return getItems(objects);
    }
}
