package io.dougluciano.storages4j.metadata;

import io.dougluciano.storages4j.core.ObjectMetadata;
import io.dougluciano.storages4j.core.StorageProvider;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public final class MetadataBuilder {

    private MetadataBuilder(){}

    public static StorageObjectMetadata buildMetadata(
            StorageProvider provider,
            String bucket,
            String objectKey,
            String originalFileName,
            ObjectMetadata stat,
            Map<String, String> customMetadata
    ){

        UUID id = UUID.randomUUID();

        Map<String, String> objectStorage = Map.of(
                "provider", provider.name(),
                "bucket", bucket,
                "objectKey", objectKey
        );

        Map<String, Object> file = Map.of(
                "originalFileName", originalFileName,
                "contentType", stat.contentType(),
                "size", stat.size(),
                "etag", stat.etag()
        );

        return new StorageObjectMetadata(
                id,
                Instant.now().toString(),
                objectStorage,
                file,
                customMetadata == null ? Map.of() : customMetadata
        );
    }
}
