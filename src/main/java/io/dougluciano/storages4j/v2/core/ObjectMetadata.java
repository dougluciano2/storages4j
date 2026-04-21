package io.dougluciano.storages4j.v2.core;

public record ObjectMetadata(
        long size,
        String contentType,
        String etag
) {
}
