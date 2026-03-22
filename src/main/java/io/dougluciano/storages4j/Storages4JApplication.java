package io.dougluciano.storages4j;

import io.dougluciano.storages4j.configuration.StorageConfiguration;
import io.dougluciano.storages4j.core.StorageClient;
import io.dougluciano.storages4j.init.Storages4JInitializer;

public class Storages4JApplication {
    public static void main(String[] args) {

        StorageConfiguration configuration = Storages4JInitializer.load();
        StorageClient client = Storages4j.fromConfiguration(configuration);

        System.out.println(client.getClass().getName());

    }
}