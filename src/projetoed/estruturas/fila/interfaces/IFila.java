package projetoed.estruturas.fila.interfaces;

import projetoed.estruturas.fila.exceptions.EmptyQueueException;

public interface IFila<T> {
    void enqueue(T elemento);
    T dequeue() throws EmptyQueueException;
    T front() throws EmptyQueueException;
    boolean isEmpty();
    int size();
}
