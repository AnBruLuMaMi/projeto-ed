package projetoed.estruturas.fila.exceptions;

public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {

    }

    public EmptyQueueException(String mensagem) {
        super(mensagem);
    }
}
