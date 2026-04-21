package io.dougluciano.storages4j.v1Outdated.metadata;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Representa a estrutura de metadados de um objeto armazenado.
 * Represents the metadata structure of a stored object.
 *
 * Este record encapsula informações relacionadas ao storage,
 * ao arquivo armazenado e aos metadados customizados.
 * This record encapsulates information related to the storage,
 * the stored file and custom metadata.
 *
 * Também fornece operações utilitárias para serialização em JSON
 * e para derivar a chave do arquivo de metadados.
 * It also provides utility operations for JSON serialization
 * and for deriving the metadata file key.
 *
 * @param id identificador único do metadata
 *           unique metadata identifier
 * @param createdAt data/hora de criação no formato ISO-8601
 *                  creation timestamp in ISO-8601 format
 * @param objectStorage informações relacionadas ao provider, bucket e object key
 *                      information related to provider, bucket and object key
 * @param file informações técnicas do arquivo armazenado
 *             technical information about the stored file
 * @param customMetadata metadados customizados fornecidos pelo usuário
 *                       user-provided custom metadata
 *
 * @author dougluciano2
 */
public record StorageObjectMetadata(
        UUID id,
        String createdAt,
        Map<String, String> objectStorage,
        Map<String, Object> file,
        Map<String, String> customMetadata
) {

    /**
     * Serializa o metadata para uma representação JSON simples.
     * Serializes the metadata into a simple JSON representation.
     *
     * @return conteúdo JSON do metadata
     *         metadata JSON content
     */
    public String toJson(){
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"createdAt\":\"" + createdAt + "\","
                + "\"objectStorage\":" + mapToJson(objectStorage) + ","
                + "\"file\":" + mapToJson(file) +  ","
                + "\"customMetadata\":" + mapToJson(customMetadata)
                + "}";
    }

    /**
     * Converte um mapa em uma representação JSON simples.
     * Converts a map into a simple JSON representation.
     *
     * @param map mapa a ser convertido
     *            map to be converted
     * @return representação JSON do mapa
     *         JSON representation of the map
     */
    private String mapToJson(Map<String, ?> map){
        return map.entrySet().stream()
                .map(m -> "\"" + m.getKey() + "\":\"" + m.getValue() + "\"")
                .collect(Collectors.joining(",", "{", "}"));
    }

    /**
     * Constrói a chave onde o arquivo metadata.json deve ser armazenado.
     * Builds the key where the metadata.json file should be stored.
     *
     * O metadata é salvo no mesmo caminho base do objeto original.
     * The metadata is stored in the same base path as the original object.
     *
     * Exemplo / Example:
     * uploads/file.pdf -> uploads/metadata.json
     *
     * @return chave do arquivo metadata.json
     *         metadata.json file key
     */
    public String buildMetadataKey(){
        String objectKey = objectStorage.get("objectKey");
        int lastSlash = objectKey.lastIndexOf('/');

        if(lastSlash < 0) {
            return "metadata.json";
        }

        return objectKey.substring(0, lastSlash + 1) + "metadata.json";
    }
}
