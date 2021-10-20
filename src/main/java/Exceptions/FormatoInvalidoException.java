package Exceptions;

public class FormatoInvalidoException extends Exception {
    public FormatoInvalidoException(String message) {
        super(message.concat(" e um formato invalido"));
    }
}
