package io.dougluciano.storages4j.v1Outdated.configuration;

import io.dougluciano.storages4j.v1Outdated.core.StorageProvider;

/**
 * Representa a configuração necessária para inicializar um client de storage.
 * Represents the configuration required to initialize a storage client.
 *
 * Esta configuração contém os dados mínimos necessários para que a biblioteca
 * selecione o provider correto e estabeleça conexão com o storage.
 * This configuration contains the minimum data required for the library
 * to select the correct provider and establish a connection with the storage.
 *
 * @param provider provider de storage que será utilizado
 *                 storage provider to be used
 * @param endpoint endpoint de acesso ao storage
 *                 storage access endpoint
 * @param accessKey chave de acesso utilizada na autenticação
 *                  access key used for authentication
 * @param secretKey chave secreta utilizada na autenticação
 *                  secret key used for authentication
 * @param bucket nome do bucket/container configurado para uso
 *               configured bucket/container name
 *
 * @author dougluciano2
 */
public record StorageConfiguration(
        StorageProvider provider,
        String endpoint,
        String accessKey,
        String secretKey,
        String bucket
) {
}
