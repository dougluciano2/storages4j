package io.dougluciano.storages4j.configuration;

import io.dougluciano.storages4j.core.StorageProvider;

public record StorageConfiguration(
        StorageProvider provider,
        String endpoint,
        String accessKey,
        String secretKey,
        String bucket
) {
}
