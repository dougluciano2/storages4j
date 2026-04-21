package io.dougluciano.storages4j.v1Outdated.infrastructure;

import io.dougluciano.storages4j.v1Outdated.configuration.StorageConfiguration;
import io.dougluciano.storages4j.v1Outdated.core.ObjectMetadata;
import io.dougluciano.storages4j.v1Outdated.core.StorageClient;
import io.dougluciano.storages4j.v1Outdated.exceptions.StorageException;
import io.dougluciano.storages4j.v1Outdated.metadata.MetadataBuilder;
import io.dougluciano.storages4j.v1Outdated.metadata.StorageObjectMetadata;
import io.minio.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * Implementação de {@link StorageClient} para o provider MinIO.
 * {@link StorageClient} implementation for the MinIO provider.
 *
 * Esta classe encapsula a comunicação com o MinIO, oferecendo operações
 * de upload, download, remoção, verificação de existência, metadados
 * e gerenciamento do bucket configurado.
 * This class encapsulates communication with MinIO, providing upload,
 * download, delete, existence check, metadata retrieval
 * and configured bucket management operations.
 *
 * Também é responsável por garantir a existência do bucket e por
 * persistir automaticamente o arquivo metadata.json após o upload
 * de um objeto principal.
 * It is also responsible for ensuring the bucket exists and for
 * automatically persisting the metadata.json file after uploading
 * a primary object.
 *
 * @author dougluciano2
 */
public class MinioStorageClient implements StorageClient {

    private final StorageConfiguration configuration;
    private final MinioClient client;


    /**
     * Cria uma nova instância do client MinIO com base na configuração informada.
     * Creates a new MinIO client instance based on the provided configuration.
     *
     * Após a inicialização do client, o bucket configurado é validado
     * e criado automaticamente caso não exista.
     * After client initialization, the configured bucket is validated
     * and automatically created if it does not exist.
     *
     * @param configuration configuração de conexão e bucket
     *                      connection and bucket configuration
     */
    public MinioStorageClient(StorageConfiguration configuration){
        this.configuration = configuration;
        this.client = MinioClient.builder()
                .endpoint(configuration.endpoint())
                .credentials(configuration.accessKey(), configuration.secretKey())
                .build();
        ensureBucketExists();
    }

    /**
     * Realiza o upload de um objeto no MinIO.
     * Uploads an object to MinIO.
     *
     * Após o upload do objeto principal, a implementação gera e persiste
     * automaticamente o arquivo metadata.json correspondente.
     * After uploading the main object, the implementation automatically
     * generates and persists the corresponding metadata.json file.
     *
     * @param objectKey chave/caminho do objeto no bucket
     *                  object key/path in the bucket
     * @param inputStream fluxo de dados do arquivo
     *                    file data stream
     * @param sizeBytes tamanho do arquivo em bytes
     *                  file size in bytes
     * @param contentType tipo de conteúdo do arquivo
     *                    file content type
     * @throws StorageException caso ocorra falha no upload
     *                          if an upload failure occurs
     */
    @Override
    public void upload(String objectKey, InputStream inputStream, long sizeBytes, String contentType) {
        try{
            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .stream(inputStream, sizeBytes, -1)
                            .contentType(contentType)
                            .build()
            );
            uploadMetadata(objectKey, extractFileName(objectKey), Map.of());

        } catch (Exception e){
            throw new StorageException("Error uploading object: " + objectKey, e);
        }
    }


    /**
     * Gera e persiste o arquivo metadata.json relacionado ao objeto enviado.
     * Generates and persists the metadata.json file related to the uploaded object.
     *
     * Este método obtém os metadados técnicos do objeto já armazenado,
     * monta a estrutura de metadata da biblioteca e realiza o upload
     * do JSON no mesmo caminho base do arquivo principal.
     * This method retrieves the technical metadata of the stored object,
     * builds the library metadata structure and uploads
     * the JSON to the same base path as the main file.
     *
     * @param objectKey chave do objeto principal
     *                  main object key
     * @param originalFileName nome original do arquivo
     *                         original file name
     * @param customMetadata metadados customizados
     *                       custom metadata
     */
    private void uploadMetadata(String objectKey, String originalFileName, Map<String, String> customMetadata){
        try {
            ObjectMetadata stat = stat(objectKey);

            StorageObjectMetadata metadata = MetadataBuilder.buildMetadata(
                    configuration.provider(),
                    configuration.bucket(),
                    objectKey,
                    originalFileName,
                    stat,
                    customMetadata
            );

            String metadataKey = metadata.buildMetadataKey();
            byte[] metadataBytes = metadata.toJson().getBytes(StandardCharsets.UTF_8);

            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(metadataKey)
                            .stream(new ByteArrayInputStream(metadataBytes), metadataBytes.length, - 1)
                            .contentType("application/json")
                            .build()
            );


        } catch (Exception e){
            throw new StorageException("Error uploading metadata for object: " + objectKey, e);
        }
    }


    /**
     * Realiza o download de um objeto armazenado no bucket configurado.
     * Downloads an object stored in the configured bucket.
     *
     * @param objectKey chave/caminho do objeto no bucket
     *                  object key/path in the bucket
     * @return fluxo de dados do objeto
     *         object data stream
     * @throws StorageException caso ocorra falha no download
     *                          if a download failure occurs
     */
    @Override
    public InputStream download(String objectKey) {
        try{
            return client.getObject(
                    GetObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .build()
            );
        } catch (Exception e){
            throw new StorageException("Error downloading object: " + objectKey, e);
        }
    }

    /**
     * Remove um objeto do bucket configurado.
     * Deletes an object from the configured bucket.
     *
     * @param objectKey chave/caminho do objeto
     *                  object key/path
     * @throws StorageException caso ocorra falha na remoção
     *                          if deletion fails
     */
    @Override
    public void delete(String objectKey) {
        try{
            client.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .build()
            );
        } catch (Exception e){
            throw new StorageException("Error deleting object: " +objectKey, e);
        }
    }

    /**
     * Verifica se um objeto existe no bucket configurado.
     * Checks whether an object exists in the configured bucket.
     *
     * @param objectKey chave/caminho do objeto
     *                  object key/path
     * @return true se o objeto existir, false caso contrário
     *         true if the object exists, false otherwise
     */
    @Override
    public boolean exists(String objectKey) {
        try{
            client.statObject(
                    StatObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .build()
            );
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Verifica se o bucket configurado existe.
     * Checks whether the configured bucket exists.
     *
     * @return true se o bucket existir, false caso contrário
     *         true if the bucket exists, false otherwise
     * @throws StorageException caso ocorra falha na verificação
     *                          if bucket verification fails
     */
    @Override
    public boolean bucketExists() {
        try{
            return client.bucketExists(
              BucketExistsArgs.builder()
                      .bucket(configuration.bucket())
                      .build()
            );
        } catch (Exception e) {
            throw new StorageException("Error checking bucket existence: " + configuration.bucket(), e);
        }
    }

    /**
     * Cria o bucket configurado caso ele ainda não exista.
     * Creates the configured bucket if it does not already exist.
     *
     * @throws StorageException caso ocorra falha na criação
     *                          if bucket creation fails
     */
    @Override
    public void createBucketIfNotExists() {
        try{
            if (!bucketExists()){
                client.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(configuration.bucket())
                                .build()
                );
            }
        } catch (Exception e){
            throw new StorageException("Error creating bucket: " + configuration.bucket(), e);
        }
    }

    /**
     * Obtém os metadados técnicos de um objeto armazenado.
     * Retrieves the technical metadata of a stored object.
     *
     * @param objectKey chave/caminho do objeto
     *                  object key/path
     * @return metadados técnicos do objeto
     *         technical metadata of the object
     * @throws StorageException caso ocorra falha na consulta
     *                          if metadata retrieval fails
     */
    @Override
    public ObjectMetadata stat(String objectKey) {
        try{
            StatObjectResponse response = client.statObject(
                    StatObjectArgs.builder()
                            .bucket(configuration.bucket())
                            .object(objectKey)
                            .build()
            );

            return new ObjectMetadata(
                    response.size(),
                    response.contentType(),
                    response.etag()
            );
        } catch (Exception e){
            throw new StorageException("Error retrieving metadata for object: " + objectKey, e);
        }
    }


    /**
     * Garante que o bucket configurado exista antes do uso do client.
     * Ensures that the configured bucket exists before the client is used.
     */
    private void ensureBucketExists(){
        if(!bucketExists()) { createBucketIfNotExists(); }
    }

    /**
     * Extrai o nome do arquivo a partir de uma object key.
     * Extracts the file name from an object key.
     *
     * @param objectKey chave/caminho do objeto
     *                  object key/path
     * @return nome do arquivo extraído da chave
     *         file name extracted from the key
     */
    private String extractFileName(String objectKey) {
        int lastSlash = objectKey.lastIndexOf('/');
        return lastSlash >= 0 ? objectKey.substring(lastSlash + 1) : objectKey;
    }
}
