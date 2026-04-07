package io.dougluciano.storages4j.core;

/**
 * Enumeração dos providers de armazenamento suportados pela biblioteca.
 * Enumeration of storage providers supported by the library.
 *
 * Define quais implementações de storage podem ser utilizadas,
 * permitindo a abstração entre diferentes vendors.
 * Defines which storage implementations can be used,
 * enabling abstraction across different vendors.
 *
 * Novos providers podem ser adicionados conforme a biblioteca evolui.
 * New providers can be added as the library evolves.
 *
 * @author dougluciano2
 */
public enum StorageProvider {
    MINIO,
    S3,
    LOCAL
}
