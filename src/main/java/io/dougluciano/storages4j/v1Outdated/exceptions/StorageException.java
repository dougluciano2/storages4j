package io.dougluciano.storages4j.v1Outdated.exceptions;

/**
 * Exceção base da biblioteca para erros relacionados a operações de storage.
 * Base library exception for errors related to storage operations.
 *
 * Esta exceção encapsula falhas ocorridas durante operações como upload,
 * download, remoção, consulta de metadados e gerenciamento de bucket.
 * This exception encapsulates failures that occur during operations such as upload,
 * download, deletion, metadata retrieval and bucket management.
 *
 * O objetivo é abstrair exceções específicas do provider utilizado,
 * mantendo a API da biblioteca desacoplada de implementações externas.
 * Its purpose is to abstract provider-specific exceptions,
 * keeping the library API decoupled from external implementations.
 *
 * @author dougluciano2
 */
public class StorageException extends RuntimeException{

    /**
     * Cria uma nova exceção de storage com uma mensagem descritiva.
     * Creates a new storage exception with a descriptive message.
     *
     * @param message mensagem de erro
     *                error message
     */
    public StorageException(String message){
        super(message);
    }

    /**
     * Cria uma nova exceção de storage com mensagem e causa original.
     * Creates a new storage exception with message and original cause.
     *
     * @param message mensagem de erro
     *                error message
     * @param cause causa original da exceção
     *              original exception cause
     */
    public StorageException(String message, Throwable cause){
        super(message, cause);
    }
}
