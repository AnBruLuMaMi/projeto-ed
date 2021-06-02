package projetoed.estruturas.pilha;

import projetoed.estruturas.pilha.interfaces.IPilhaLSE;

import java.util.EmptyStackException;

public class PilhaLSE<T> implements IPilhaLSE<T> {
    private Nodo<T> elementoTopo;
    private int quantidadeElementosPilha;

    public PilhaLSE() {
        elementoTopo = null;
        quantidadeElementosPilha = 0;
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();

        return elementoTopo.getElemento();
    }

    @Override
    public void push(T elemento) {
        Nodo<T> nodo = new Nodo<T>(elemento, elementoTopo);
        elementoTopo = nodo;
        quantidadeElementosPilha++;
    }

    @Override
    public T pop() throws EmptyStackException {
        Nodo<T> aux = elementoTopo;

        if (isEmpty())
            throw new EmptyStackException();

        T temp = elementoTopo.getElemento();
        elementoTopo = elementoTopo.getProximo();
        aux.setProximo(null);
        quantidadeElementosPilha--;

        return temp;
    }

    @Override
    public int size() {
        return quantidadeElementosPilha;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        String arranjoString = "[";

        Nodo<T> nodo = elementoTopo;

        while (nodo != null) {
            arranjoString += nodo.getElemento();

            nodo = nodo.getProximo();
            if (nodo != null) {
                arranjoString += ",";
            }
        }

        arranjoString += "]";

        return arranjoString;
    }
}
