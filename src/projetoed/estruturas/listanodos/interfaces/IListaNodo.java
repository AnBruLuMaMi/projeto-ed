package projetoed.estruturas.listanodos.interfaces;

import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;

import java.util.Iterator;

public interface IListaNodo<T> extends Iterable<T> {
    INodo<T> first();
    INodo<T> last();
    INodo<T> next(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException;
    INodo<T> prev(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException;
    void addFirst(T elemento);
    void addLast(T elemento);
    void addAfter(INodo<T> nodo, T elemento) throws NodoInvalidoException;
    void addBefore(INodo<T> nodo, T elemento) throws NodoInvalidoException;
    T remove(INodo<T> nodo) throws NodoInvalidoException;
    T set(INodo<T> nodo, T elemento) throws NodoInvalidoException;
    Iterator<T> iterator();
    int size();
    boolean isEmpty();
}
