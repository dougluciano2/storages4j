package io.dougluciano.storages4j.init;

import io.dougluciano.storages4j.configuration.StorageConfiguration;
import io.dougluciano.storages4j.core.StorageProvider;

import java.io.InputStream;
import java.util.Properties;

public final class Storages4JInitializer {

    private static final String DEFAULT_FILE = "storages4j.properties";

    public static StorageConfiguration load(){
        return load(DEFAULT_FILE);
    }

    public static StorageConfiguration load(String fileName){
        Properties properties = new Properties();



        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)){
            if (inputStream == null){
                throw new IllegalStateException("Configuration file not found: " + fileName);
            }

            properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("And error has ocurred to start configuration file: " + fileName, e);
        }

        String provider = require(properties, "storages4j.provider");
        String endpoint = require(properties, "storages4j.endpoint");
        String accessKey = require(properties, "storages4j.accessKey");
        String secretKey = require(properties, "storages4j.secretKey");
        String bucket = require(properties, "storages4j.bucket");

        return new StorageConfiguration(
                StorageProvider.valueOf(provider.toUpperCase()),
                endpoint,
                accessKey,
                secretKey,
                bucket
        );

    }

    private static String require(Properties properties, String key){
        String value = properties.getProperty(key);

        if (value == null || value.isBlank()){
            throw new IllegalStateException("Missing required configuration: " + key);
        }

        return value;
    }
}
