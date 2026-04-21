package io.dougluciano.storages4j.v2.provider;

import java.io.InputStream;

public interface StorageProviderClient {

    void putObject(String objectKey, InputStream inputStream, long sizeBytes, String contentType);

    InputStream getObject(String objectKey);

    void removeObject(String objectKey);

    boolean existis(String objectKey);

    boolean bucketExists();

    void createBucketIfNotExists();
}
