package io.dougluciano.storages4j.core;

import java.io.InputStream;

public interface StorageClient {

    void upload(String objectKey, InputStream inputStream, long sizeBytes, String contentType);

    InputStream download(String objectKey);

    void delete(String objectKey);

    boolean exists(String objectKey);

}
