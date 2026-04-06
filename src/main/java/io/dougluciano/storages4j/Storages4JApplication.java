package io.dougluciano.storages4j;

import io.dougluciano.storages4j.configuration.StorageConfiguration;
import io.dougluciano.storages4j.core.ObjectMetadata;
import io.dougluciano.storages4j.core.StorageClient;
import io.dougluciano.storages4j.init.Storages4JInitializer;
import io.dougluciano.storages4j.metadata.MetadataBuilder;
import io.dougluciano.storages4j.metadata.StorageObjectMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class Storages4JApplication {
    public static void main(String[] args) throws Exception {

        StorageConfiguration configuration = Storages4JInitializer.load();
        StorageClient client = Storages4j.fromConfiguration(configuration);

        System.out.println(client.getClass().getName());

        //upload
        try(InputStream inputStream = new FileInputStream("teste.pdf")){
            client.upload("uploads/teste.pdf", inputStream, new File("teste.pdf").length(), "application/pdf");
        }

        //exists
        System.out.println("Exists: " + client.exists("uploads/teste.pdf"));

        //download
        try(InputStream inputStream = client.download("uploads/teste.pdf")){
            Files.copy(inputStream, Paths.get("downloaded.pdf"), StandardCopyOption.REPLACE_EXISTING);
        }

        // metadata
        ObjectMetadata metadata = client.stat("uploads/teste.pdf");
        System.out.println(metadata);

        //delete
        client.delete("uploads/teste.pdf");

        System.out.println("Exists after delete: " + client.exists("uploads/teste.pdf"));
    }
}