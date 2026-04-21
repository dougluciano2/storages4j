package io.dougluciano.storages4j.v2.provider;

import io.dougluciano.storages4j.v2.configuration.Storages4JConfiguration;
import io.dougluciano.storages4j.v2.logMessages.LogMessages;
import io.minio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class MinioProviderClient implements StorageProviderClient {

    private final Storages4JConfiguration configuration;
    private final MinioClient minioClient;
    private static final Logger log = LoggerFactory.getLogger(MinioProviderClient.class);

    public MinioProviderClient (Storages4JConfiguration configuration, MinioClient minioClient){
        this.configuration = configuration;
        this.minioClient = minioClient;
    }


    @Override
    public void putObject(String objectKey, InputStream inputStream, long sizeBytes, String contentType) {
        try{
            log.info(LogMessages.UPLOAD_START.getMessage(),objectKey, configuration.getBucket());
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(configuration.getBucket())
                            .object(objectKey)
                            .stream(inputStream, sizeBytes, -1)
                            .contentType(contentType)
                            .build()
            );
            log.info(LogMessages.UPLOAD_SUCCESS.getMessage(),objectKey, configuration.getBucket());

        } catch (Exception e){
            log.error(LogMessages.UPLOAD_ERROR.getMessage(),objectKey, configuration.getBucket(), e);
            throw new RuntimeException("Error uploading object: " + objectKey, e);
        }
    }

    @Override
    public InputStream getObject(String objectKey) {
        log.info(LogMessages.DOWNLOAD_START.getMessage(), objectKey, configuration.getBucket());
        try{
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(configuration.getBucket())
                            .object(objectKey)
                            .build()
            );

            log.info(LogMessages.DOWNLOAD_SUCCESS.getMessage(), objectKey, configuration.getBucket());
            return stream;

        } catch (Exception e) {
            log.error(LogMessages.DOWNLOAD_ERROR.getMessage(), objectKey, configuration.getBucket(), e);
            throw new RuntimeException("Error downloading object: " + objectKey, e);
        }
    }

    @Override
    public void removeObject(String objectKey) {
        try {
            log.info(LogMessages.OBJECT_REMOVE_START.getMessage(), objectKey, configuration.getBucket());

            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(configuration.getBucket())
                            .object(objectKey)
                            .build()
            );

            log.info(LogMessages.OBJECT_REMOVE_SUCCESS.getMessage(), objectKey, configuration.getBucket());
        } catch (Exception e) {
            log.error(LogMessages.OBJECT_REMOVE_ERROR.getMessage(), objectKey, configuration.getBucket(), e);
            throw new RuntimeException("Erro deleting object: " + objectKey,e);
        }
    }

    @Override
    public boolean existis(String objectKey) {
        log.info(LogMessages.OBJECT_EXISTS_CHECK.getMessage(), objectKey, configuration.getBucket());
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(configuration.getBucket())
                            .object(objectKey)
                            .build()
            );

            log.info(LogMessages.OBJECT_EXISTS_TRUE.getMessage(), objectKey, configuration.getBucket());
            return true;
        } catch (Exception e) {
            log.warn(LogMessages.OBJECT_EXISTS_FALSE.getMessage(), objectKey, configuration.getBucket(), e);
            return false;
        }
    }

    @Override
    public boolean bucketExists() {
        try{
            log.info(LogMessages.BUCKET_CHECK.getMessage(), configuration.getBucket());
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(configuration.getBucket())
                            .build()
            );

            if(exists){
                log.info(LogMessages.BUCKET_CHECK_SUCCESS.getMessage(), configuration.getBucket());
            }
            return exists;


        } catch (Exception e){
            log.error(LogMessages.BUCKET_CHECK_ERROR.getMessage(), configuration.getBucket(), e);
            throw new RuntimeException("Error checking bucket existence", e);
        }
    }

    @Override
    public void createBucketIfNotExists() {
        try {
            log.info(LogMessages.BUCKET_CREATE.getMessage(), configuration.getBucket());
            if(!bucketExists()){
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(configuration.getBucket())
                                .build()
                );
                log.info(LogMessages.BUCKET_CREATE_SUCCESS.getMessage(), configuration.getBucket());
            }
        } catch (Exception e) {
            log.error(LogMessages.BUCKET_CREATE_ERROR.getMessage(), configuration.getBucket(), e);
            throw new RuntimeException("Error creating bucket", e);
        }
    }
}
