package io.dougluciano.storages4j.v1Outdated.core;

/**
 * Representa os metadados técnicos de um objeto armazenado.
 * Represents the technical metadata of a stored object.
 *
 * Contém informações retornadas diretamente pelo provider de storage,
 * como tamanho, tipo de conteúdo e identificador único (etag).
 * Contains information returned directly by the storage provider,
 * such as size, content type and unique identifier (etag).
 *
 * @param size tamanho do objeto em bytes
 *             object size in bytes
 * @param contentType tipo de conteúdo do objeto (MIME type)
 *                    object content type (MIME type)
 * @param etag identificador único do objeto (hash/etag)
 *             unique object identifier (hash/etag)
 *
 * @author dougluciano2
 */
public record ObjectMetadata(
        long size,
        String contentType,
        String etag
) {
}
