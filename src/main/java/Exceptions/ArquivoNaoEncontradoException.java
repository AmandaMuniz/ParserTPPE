package Exceptions;

public class ArquivoNaoEncontradoException extends Exception {
    public ArquivoNaoEncontradoException(String message) {
        super(message.concat(" não encontrado"));
    }
}
