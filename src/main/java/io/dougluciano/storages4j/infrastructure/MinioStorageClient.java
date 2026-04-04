package io.dougluciano.storages4j.infrastructure;

import io.dougluciano.storages4j.configuration.StorageConfiguration;
import io.dougluciano.storages4j.core.StorageClient;
import io.dougluciano.storages4j.exceptions.StorageException;
import io.minio.*;

import java.io.InputStream;

public class MinioStorageClient implements StorageClient {

    private final StorageConfiguration configuration;

    private final MinioClient client;

    public MinioStorageClient(StorageConfiguration configuration){
        this.configuration = configuration;
        this.client = MinioClient.builder()
                .endpoint(configuration.endpoint())
                .credentials(configuration.accessKey(), configuration.secretKey())
                .build();
    }

    @Override
    public void upload(String objectKey, InputStream inputStream, long sizeBytes, String contentType) {
        try{
            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .stream(inputStream, sizeBytes, -1)
                            .contentType(contentType)
                            .build()
            );
        } catch (Exception e){
            throw new StorageException("Error uploading object: " + objectKey, e);
        }
    }

    @Override
    public InputStream download(String objectKey) {
        try{
            return client.getObject(
                    GetObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .build()
            );
        } catch (Exception e){
            throw new StorageException("Error downloading object: " + objectKey, e);
        }
    }

    @Override
    public void delete(String objectKey) {
        try{
            client.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .build()
            );
        } catch (Exception e){
            throw new StorageException("Error deleting object: " +objectKey, e);
        }
    }

    @Override
    public boolean exists(String objectKey) {
        try{
            client.statObject(
                    StatObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .build()
            );
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
