package projetoed.estruturas.pilha.interfaces;

import java.util.EmptyStackException;

public interface IPilhaLSE<T> {
    public T top() throws EmptyStackException;
    public void push(T element);
    public T pop() throws EmptyStackException;
    public int size();
    public boolean isEmpty();
}
