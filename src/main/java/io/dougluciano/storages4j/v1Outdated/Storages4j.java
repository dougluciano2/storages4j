package io.dougluciano.storages4j.v1Outdated;


import io.dougluciano.storages4j.v1Outdated.configuration.StorageConfiguration;
import io.dougluciano.storages4j.v1Outdated.core.StorageClient;
import io.dougluciano.storages4j.v1Outdated.exceptions.StorageException;
import io.dougluciano.storages4j.v1Outdated.infrastructure.MinioStorageClient;

/**
 * Classe fábrica responsável por instanciar o {@link StorageClient}
 * de acordo com o provider configurado.
 * Factory class responsible for instantiating the {@link StorageClient}
 * based on the configured provider.
 *
 * Esta classe centraliza a criação das implementações de storage,
 * permitindo desacoplar o código do usuário das implementações concretas.
 * This class centralizes the creation of storage implementations,
 * allowing the user code to remain decoupled from concrete implementations.
 *
 * @author dougluciano2
 */
public final class Storages4j {

    private Storages4j(){}

    /**
     * Cria uma instância de {@link StorageClient} com base na configuração informada.
     * Creates an instance of {@link StorageClient} based on the provided configuration.
     *
     * @param configuration configuração de storage
     *                      storage configuration
     * @return implementação de {@link StorageClient} correspondente ao provider
     *         {@link StorageClient} implementation corresponding to the provider
     *
     * @throws StorageException caso o provider não seja suportado
     *                          if the provider is not supported
     */
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
