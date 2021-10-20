package Exceptions;

public class EscritaNaoPermitidaException extends Exception {
    public EscritaNaoPermitidaException(String message) {
        super(message.concat(" não pode ser escrito"));
    }
}
