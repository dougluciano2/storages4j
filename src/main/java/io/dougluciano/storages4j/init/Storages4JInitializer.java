package io.dougluciano.storages4j.init;

import io.dougluciano.storages4j.configuration.StorageConfiguration;
import io.dougluciano.storages4j.core.StorageProvider;

import java.io.InputStream;
import java.util.Properties;

/**
 * Classe responsável por carregar a configuração de storage a partir de um arquivo properties.
 * Class responsible for loading storage configuration from a properties file.
 *
 * Esta classe lê um arquivo de configuração localizado no classpath
 * e constrói uma instância de {@link StorageConfiguration}.
 * This class reads a configuration file from the classpath
 * and builds a {@link StorageConfiguration} instance.
 *
 * O arquivo padrão esperado é "storages4j.properties", mas também é possível
 * informar um nome customizado.
 * The default expected file is "storages4j.properties", but a custom file name
 * can also be provided.
 *
 * @author dougluciano2
 */
public final class Storages4JInitializer {

    private static final String DEFAULT_FILE = "storages4j.properties";

    /**
     * Carrega a configuração utilizando o arquivo padrão.
     * Loads configuration using the default file.
     *
     * @return configuração de storage carregada
     *         loaded storage configuration
     */
    public static StorageConfiguration load(){
        return load(DEFAULT_FILE);
    }

    /**
     * Carrega a configuração a partir de um arquivo properties específico.
     * Loads configuration from a specific properties file.
     *
     * O arquivo deve estar disponível no classpath da aplicação.
     * The file must be available in the application classpath.
     *
     * @param fileName nome do arquivo de configuração
     *                 configuration file name
     * @return configuração de storage carregada
     *         loaded storage configuration
     * @throws IllegalStateException caso o arquivo não seja encontrado
     *                               if the file is not found
     * @throws RuntimeException caso ocorra erro na leitura do arquivo
     *                          if an error occurs while reading the file
     */
    public static StorageConfiguration load(String fileName){
        Properties properties = new Properties();

        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)){
            if (inputStream == null){
                throw new IllegalStateException("Configuration file not found: " + fileName);
            }

            properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("And error has ocurred to start configuration file: " + fileName, e);
        }

        String provider = require(properties, "storages4j.provider");
        String endpoint = require(properties, "storages4j.endpoint");
        String accessKey = require(properties, "storages4j.accessKey");
        String secretKey = require(properties, "storages4j.secretKey");
        String bucket = require(properties, "storages4j.bucket");

        return new StorageConfiguration(
                StorageProvider.valueOf(provider.toUpperCase()),
                endpoint,
                accessKey,
                secretKey,
                bucket
        );

    }

    /**
     * Recupera uma propriedade obrigatória do arquivo de configuração.
     * Retrieves a required property from the configuration file.
     *
     * Caso a propriedade não exista ou esteja vazia, uma exceção será lançada.
     * If the property is missing or blank, an exception will be thrown.
     *
     * @param properties propriedades carregadas
     *                   loaded properties
     * @param key chave da propriedade
     *            property key
     * @return valor da propriedade
     *         property value
     * @throws IllegalStateException caso a propriedade não esteja definida
     *                               if the property is not defined
     */
    private static String require(Properties properties, String key){
        String value = properties.getProperty(key);

        if (value == null || value.isBlank()){
            throw new IllegalStateException("Missing required configuration: " + key);
        }

        return value;
    }
}
