package io.dougluciano.storages4j.core;

public record ObjectMetadata(
        long size,
        String contentType,
        String etag
) {
}
