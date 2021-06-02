package projetoed.estruturas.arvoregenerica.interfaces;

import projetoed.estruturas.arvoregenerica.exceptions.ArvoreVaziaException;
import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.INodo;

import java.util.Iterator;

public interface IArvore<T> extends Iterable<T> {
    Iterable<INodo<T>> positions();
    T replace(INodo<T> nodo, T elemento) throws NodoInvalidoException;
    INodo<T> root() throws ArvoreVaziaException;
    INodo<T> parent(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException;
    Iterable<INodo<T>> children(INodo<T> nodo) throws NodoInvalidoException;
    boolean isInternal(INodo<T> nodo) throws NodoInvalidoException;
    boolean isExternal(INodo<T> nodo) throws NodoInvalidoException;
    boolean isRoot(INodo<T> nodo) throws NodoInvalidoException;
    Iterator<T> iterator();
    int size();
    boolean isEmpty();
}
