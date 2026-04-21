package io.dougluciano.storages4j.v2.logMessages;

public enum LogMessages {
    UPLOAD_START("Starting upload for object: {} | into bucket: {}"),
    UPLOAD_SUCCESS("Upload complete for object: {} | into bucket: {}"),
    UPLOAD_ERROR("Error uploading object: {} | into bucket: {}"),

    DOWNLOAD_START("Starting download of object name: {} on bucket: {}"),
    DOWNLOAD_SUCCESS("Download of object name: {} on bucket: {} Success!"),
    DOWNLOAD_ERROR("Error downloading object name: {} on bucket: {}"),

    OBJECT_EXISTS_CHECK("Getting information of object: {} on bucket: {}"),
    OBJECT_EXISTS_TRUE("Success on getting information of object: {} on bucket: {}"),
    OBJECT_EXISTS_FALSE("Error on getting information of object: {} on bucket: {}"),

    BUCKET_CHECK("Checking if bucket name: {} exists"),
    BUCKET_CHECK_SUCCESS("Bucket name: {} exists! Success!"),
    BUCKET_CHECK_ERROR("Error checking bucket name: {}"),
    BUCKET_CREATE("Creating bucket name: {}"),
    BUCKET_CREATE_SUCCESS("Success on creating bucket name: {}"),
    BUCKET_CREATE_ERROR("Error creating bucket name: {}"),

    OBJECT_REMOVE_START("Trying to remove object: {} on bucket: {}"),
    OBJECT_REMOVE_SUCCESS("Success on removing object name: {}, on bucket: {}"),
    OBJECT_REMOVE_ERROR("Error on removing object name: {}, on bucket: {}");

    private final String message;

    LogMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
