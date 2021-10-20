package Exceptions;

public class DelimitadorInvalidoException extends Exception {
    public DelimitadorInvalidoException(String message) {
        super(message.concat(" nao é válido como delimitador"));
    }
}
