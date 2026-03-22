package io.dougluciano.storages4j.infrastructure;

import io.dougluciano.storages4j.configuration.StorageConfiguration;
import io.dougluciano.storages4j.core.StorageClient;

public class MinioStorageClient implements StorageClient {

    private final StorageConfiguration configuration;

    public MinioStorageClient(StorageConfiguration configuration){
        this.configuration = configuration;
    }
}
