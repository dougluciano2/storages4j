package io.dougluciano.storages4j.v1Outdated.core;

import java.io.InputStream;

/**
 * Contrato principal para operações de armazenamento de objetos.
 * Main contract for object storage operations.
 *
 * Esta interface define as operações comuns que um provider de storage
 * deve implementar, abstraindo detalhes específicos de vendor.
 * This interface defines the common operations that a storage provider
 * must implement, abstracting vendor-specific details.
 *
 * @author dougluciano2
 */
public interface StorageClient {

    /**
     * Realiza o upload de um objeto para o storage.
     * Uploads an object to the storage.
     *
     * @param objectKey chave/caminho único do objeto no storage
     *                  unique key/path of the object in the storage
     * @param inputStream fluxo de dados do arquivo
     *                    file data stream
     * @param sizeBytes tamanho do arquivo em bytes
     *                  file size in bytes
     * @param contentType tipo de conteúdo do arquivo
     *                    file content type
     */
    void upload(String objectKey, InputStream inputStream, long sizeBytes, String contentType);

    /**
     * Realiza o download de um objeto do storage.
     * Downloads an object from the storage.
     *
     * @param objectKey chave/caminho único do objeto no storage
     *                  unique key/path of the object in the storage
     * @return fluxo de dados do objeto armazenado
     *         data stream of the stored object
     */
    InputStream download(String objectKey);

    /**
     * Remove um objeto do storage.
     * Deletes an object from the storage.
     *
     * @param objectKey chave/caminho único do objeto no storage
     *                  unique key/path of the object in the storage
     */
    void delete(String objectKey);

    /**
     * Verifica se um objeto existe no storage.
     * Checks whether an object exists in the storage.
     *
     * @param objectKey chave/caminho único do objeto no storage
     *                  unique key/path of the object in the storage
     * @return true se o objeto existir, false caso contrário
     *         true if the object exists, false otherwise
     */
    boolean exists(String objectKey);

    /**
     * Verifica se o bucket configurado existe.
     * Checks whether the configured bucket exists.
     *
     * @return true se o bucket existir, false caso contrário
     *         true if the bucket exists, false otherwise
     */
    boolean bucketExists();

    /**
     * Cria o bucket configurado caso ele ainda não exista.
     * Creates the configured bucket if it does not already exist.
     */
    void createBucketIfNotExists();

    /**
     * Obtém os metadados técnicos de um objeto armazenado.
     * Retrieves the technical metadata of a stored object.
     *
     * @param objectKey chave/caminho único do objeto no storage
     *                  unique key/path of the object in the storage
     * @return metadados técnicos do objeto
     *         technical metadata of the object
     */
    ObjectMetadata stat(String objectKey);

}
