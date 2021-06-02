package projetoed.estruturas.arvorebinaria.interfaces;

import projetoed.estruturas.arvoregenerica.interfaces.IArvore;
import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.INodo;

public interface IArvoreBinaria<T> extends IArvore<T> {
    INodo<T> left(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException;
    INodo<T> right(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException;
    boolean hasLeft(INodo<T> nodo) throws NodoInvalidoException;
    boolean hasRight(INodo<T> nodo) throws NodoInvalidoException;
}
