package io.dougluciano.storages4j;


import io.dougluciano.storages4j.configuration.StorageConfiguration;
import io.dougluciano.storages4j.core.StorageClient;
import io.dougluciano.storages4j.exceptions.StorageException;
import io.dougluciano.storages4j.infrastructure.MinioStorageClient;

public final class Storages4j {

    private Storages4j(){}

    public static StorageClient fromConfiguration(StorageConfiguration configuration){

        if (configuration == null){
            throw new IllegalArgumentException("StorageConfiguration cannot be null!");
        }

        return switch (configuration.provider()){
            case MINIO -> new MinioStorageClient(configuration);
            case S3 -> throw new StorageException("S3 Client not implemented yet");
            case LOCAL -> throw new StorageException("Local Storage not implemented yet");
        };
    }
}
