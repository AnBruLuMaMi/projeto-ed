package projetoed.estruturas.fila;

import projetoed.estruturas.fila.exceptions.EmptyQueueException;
import projetoed.estruturas.fila.interfaces.IFila;
import projetoed.estruturas.pilha.Nodo;

public class FilaLSE<T> implements IFila<T> {
    private Nodo<T> head;
    private Nodo<T> tail;

    private int quantidadeElementosFila;

    public FilaLSE() {
        head = null;
        tail = null;
        quantidadeElementosFila = 0;
    }

    @Override
    public void enqueue(T elemento) {
        Nodo<T> nodo = new Nodo<T>(elemento, null);

        if (isEmpty())
            head = nodo;
        else
            tail.setProximo(nodo);

        tail = nodo;

        quantidadeElementosFila++;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("A fila está vazia.");

        T tmp = head.getElemento();

        head = head.getProximo();

        quantidadeElementosFila--;

        if (isEmpty())
            tail = null;

        return tmp;
    }

    @Override
    public T front() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("A fila está vazia.");

        return head.getElemento();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return quantidadeElementosFila;
    }

    @Override
    public String toString() {
        String arranjoString = "[";

        Nodo<T> nodo = head;

        while (nodo != null) {
            arranjoString += nodo.getElemento();

            nodo = nodo.getProximo();

            if (nodo != null) {
                arranjoString += ", ";
            }
        }

        arranjoString += "]";

        return arranjoString;
    }
}
