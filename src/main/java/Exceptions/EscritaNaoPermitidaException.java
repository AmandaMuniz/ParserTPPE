package Exceptions;

public class EscritaNaoPermitidaException extends Exception {
    public EscritaNaoPermitidaException(String message) {
        super(message.concat(" nao pode ser escrito"));
    }
}
