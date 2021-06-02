package projetoed.estruturas.listanodos;

import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.exceptions.ListaVaziaException;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

import java.util.Iterator;

public class ListaNodo<T> implements IListaNodo<T> {
    private DNodo<T> header, trailler;
    private int quantidadeElementosLista;

    public ListaNodo() {
        quantidadeElementosLista = 0;
        header = new DNodo<T>(null, null, null, this);
        trailler = new DNodo<T>(header, null, null, this);
        header.setProximo(trailler); //
    }

    private DNodo<T> validarNodo(INodo<T> nodo) throws NodoInvalidoException {

        if (nodo == null)
            throw new NodoInvalidoException("O nodo não pode ser nulo");

        if (nodo == header)
            throw new NodoInvalidoException("O nodo header não é válido");

        if (nodo == trailler)
            throw new NodoInvalidoException("O nodo trailler não é válido");

        try {
            DNodo<T> temp = (DNodo<T>) nodo;

            if ((temp.getAnterior() == null) || (temp.getProximo() == null))
                throw new NodoInvalidoException("O nodo não pertence a uma lista de nodos válida");

            if (temp.getLista() != this)
                throw new NodoInvalidoException("O nodo não pertence a essa lista.");

            return temp;
        } catch (ClassCastException e) {
            throw new NodoInvalidoException("O nodo não possui o mesmo tipo da lista");
        }
    }

    @Override
    public INodo<T> first() throws ListaVaziaException {
        if (isEmpty())
            throw new ListaVaziaException("A lista está vazia.");

        return header.getProximo();
    }

    @Override
    public INodo<T> last() {
        if (isEmpty())
            throw new ListaVaziaException("A lista está vazia.");

        return trailler.getAnterior();
    }

    @Override
    public INodo<T> next(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        DNodo<T> novoNodo = validarNodo(nodo);
        DNodo<T> proximoNodo = novoNodo.getProximo();

        if (proximoNodo == trailler)
            throw new LimiteVioladoException("Não é possível ir além do final da lista");

        return proximoNodo;
    }

    @Override
    public INodo<T> prev(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        DNodo<T> nodoNovo = validarNodo(nodo);
        DNodo<T> anterior = nodoNovo.getAnterior();

        if (anterior == header)
            throw new LimiteVioladoException("Não é possível ir além do começo da lista");

        return anterior;
    }

    @Override
    public void addFirst(T elemento) {
        quantidadeElementosLista++;

        DNodo<T> novoNodo = new DNodo<T>(header, header.getProximo(), elemento, this);

        header.getProximo().setAnterior(novoNodo);
        header.setProximo(novoNodo);
    }

    @Override
    public void addLast(T elemento) {
        quantidadeElementosLista++;

        DNodo<T> novoNodo = new DNodo<T>(trailler.getAnterior(), trailler, elemento, this);

        trailler.getAnterior().setProximo(novoNodo);
        trailler.setAnterior(novoNodo);
    }

    @Override
    public void addAfter(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        DNodo<T> nodoValido = validarNodo(nodo);

        quantidadeElementosLista++;

        DNodo<T> novoNodo = new DNodo<T>(nodoValido, nodoValido.getProximo(), elemento, this);

        nodoValido.getProximo().setAnterior(novoNodo);
        nodoValido.setProximo(novoNodo);
    }

    @Override
    public void addBefore(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        DNodo<T> nodoValido = validarNodo(nodo);

        quantidadeElementosLista++;

        DNodo<T> novoNodo = new DNodo<T>(nodoValido.getAnterior(), nodoValido, elemento, this);

        nodoValido.getAnterior().setProximo(novoNodo);
        nodoValido.setAnterior(novoNodo);
    }

    @Override
    public T remove(INodo<T> nodo) throws NodoInvalidoException {
        DNodo<T> nodoValido = validarNodo(nodo);

        quantidadeElementosLista--;

        DNodo<T> nodoAnterior = nodoValido.getAnterior();
        DNodo<T> nodoProximo = nodoValido.getProximo();

        nodoAnterior.setProximo(nodoProximo);
        nodoProximo.setAnterior(nodoAnterior);

        T elementoNodo = nodoValido.element();

        nodoValido.setProximo(null);
        nodoValido.setAnterior(null);

        return elementoNodo;
    }

    @Override
    public T set(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        DNodo<T> nodoValido = validarNodo(nodo);

        T elementoAntigo = nodoValido.element();

        nodoValido.setElemento(elemento);

        return elementoAntigo;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementIterator<T>(this);
    }

    @Override
    public int size() {
        return quantidadeElementosLista;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private static <T> String toString(IListaNodo<T> l) {
        String s = "";

        for (T i: l) { s += ", " + i; }

        s = (s.length() == 0 ? s : s.substring(2));

        return "[" + s + "]";
    }

    @Override
    public String toString() {
        return toString(this);
    }
}
