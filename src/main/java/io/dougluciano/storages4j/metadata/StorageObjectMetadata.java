package io.dougluciano.storages4j.metadata;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public record StorageObjectMetadata(
        UUID id,
        String createdAt,
        Map<String, String> objectStorage,
        Map<String, Object> file,
        Map<String, String> customMetadata
) {

    public String toJson(){
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"createdAt\":\"" + createdAt + "\","
                + "\"objectStorage\":" + mapToJson(objectStorage) + ","
                + "\"file\":" + mapToJson(file) +  ","
                + "\"customMetadata\":" + mapToJson(customMetadata)
                + "}";
    }

    private String mapToJson(Map<String, ?> map){
        return map.entrySet().stream()
                .map(m -> "\"" + m.getKey() + "\":\"" + m.getValue() + "\"")
                .collect(Collectors.joining(",", "{", "}"));
    }

    public String buildMetadataKey(){
        String objectKey = objectStorage.get("objectKey");
        int lastSlash = objectKey.lastIndexOf('/');

        if(lastSlash < 0) {
            return "metadata.json";
        }

        return objectKey.substring(0, lastSlash + 1) + "metadata.json";
    }
}
