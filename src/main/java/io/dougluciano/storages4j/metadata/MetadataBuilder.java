package io.dougluciano.storages4j.metadata;

import io.dougluciano.storages4j.core.ObjectMetadata;
import io.dougluciano.storages4j.core.StorageProvider;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * Classe utilitária responsável por construir instâncias de {@link StorageObjectMetadata}.
 * Utility class responsible for building instances of {@link StorageObjectMetadata}.
 *
 * Centraliza a criação da estrutura de metadados da biblioteca,
 * organizando informações de storage, arquivo e metadados customizados.
 * Centralizes the creation of the library metadata structure,
 * organizing storage, file and custom metadata information.
 *
 * @author dougluciano2
 */
public final class MetadataBuilder {

    private MetadataBuilder(){}

    /**
     * Constrói um objeto de metadados para um arquivo armazenado.
     * Builds a metadata object for a stored file.
     *
     * Gera automaticamente um identificador único e a data de criação,
     * além de organizar os dados em blocos lógicos.
     * Automatically generates a unique identifier and creation timestamp,
     * while organizing the data into logical sections.
     *
     * @param provider provider de storage utilizado
     *                 storage provider in use
     * @param bucket bucket onde o objeto foi armazenado
     *               bucket where the object was stored
     * @param objectKey chave/caminho do objeto no storage
     *                  object key/path in the storage
     * @param originalFileName nome original do arquivo
     *                         original file name
     * @param stat metadados técnicos retornados pelo provider
     *             technical metadata returned by the provider
     * @param customMetadata metadados customizados fornecidos pelo usuário
     *                       user-provided custom metadata
     * @return instância de {@link StorageObjectMetadata}
     *         instance of {@link StorageObjectMetadata}
     */
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
