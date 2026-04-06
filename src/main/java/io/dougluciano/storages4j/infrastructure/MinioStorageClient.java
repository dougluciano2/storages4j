package io.dougluciano.storages4j.infrastructure;

import io.dougluciano.storages4j.configuration.StorageConfiguration;
import io.dougluciano.storages4j.core.ObjectMetadata;
import io.dougluciano.storages4j.core.StorageClient;
import io.dougluciano.storages4j.exceptions.StorageException;
import io.dougluciano.storages4j.metadata.MetadataBuilder;
import io.dougluciano.storages4j.metadata.StorageObjectMetadata;
import io.minio.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MinioStorageClient implements StorageClient {

    private final StorageConfiguration configuration;

    private final MinioClient client;

    public MinioStorageClient(StorageConfiguration configuration){
        this.configuration = configuration;
        this.client = MinioClient.builder()
                .endpoint(configuration.endpoint())
                .credentials(configuration.accessKey(), configuration.secretKey())
                .build();
        ensureBucketExists();
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

    private void uploadMetadata(String objectKey, String originalFileName, Map<String, String> customMetadata){
        try {
            ObjectMetadata stat = stat(objectKey);

            StorageObjectMetadata metadata = MetadataBuilder.buildMetadata(
                    configuration.provider(),
                    configuration.bucket(),
                    objectKey,
                    originalFileName,
                    stat,
                    customMetadata
            );

            String metadataKey = metadata.buildMetadataKey();
            byte[] metadataBytes = metadata.toJson().getBytes(StandardCharsets.UTF_8);

            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(metadataKey)
                            .stream(new ByteArrayInputStream(metadataBytes), metadataBytes.length, - 1)
                            .contentType("aplication/json")
                            .build()
            );

        } catch (Exception e){
            throw new StorageException("Error uploading metadata for object: " + objectKey, e);
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

    @Override
    public boolean bucketExists() {
        try{
            return client.bucketExists(
              BucketExistsArgs.builder()
                      .bucket(configuration.bucket())
                      .build()
            );
        } catch (Exception e) {
            throw new StorageException("Error checking bucket existence: " + configuration.bucket(), e);
        }
    }

    @Override
    public void createBucketIfNotExists() {
        try{
            if (!bucketExists()){
                client.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(configuration.bucket())
                                .build()
                );
            }
        } catch (Exception e){
            throw new StorageException("Error creating bucket: " + configuration.bucket(), e);
        }
    }

    @Override
    public ObjectMetadata stat(String objectKey) {
        try{
            StatObjectResponse response = client.statObject(
                    StatObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .build()
            );

            return new ObjectMetadata(
                    response.size(),
                    response.contentType(),
                    response.etag()
            );
        } catch (Exception e){
            throw new StorageException("Error retrieving metadata for object: " + objectKey, e);
        }
    }

    private void ensureBucketExists(){
        if(!bucketExists()) { createBucketIfNotExists(); }
    }

    private String extractFileName(String objectKey) {
        int lastSlash = objectKey.lastIndexOf('/');
        return lastSlash >= 0 ? objectKey.substring(lastSlash + 1) : objectKey;
    }
}
