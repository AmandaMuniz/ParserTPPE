package Exceptions;

public class ErroDeLeituraException extends Exception {
    public ErroDeLeituraException(String message) {
        super(message.concat(" não pode ser lido"));
    }
}
