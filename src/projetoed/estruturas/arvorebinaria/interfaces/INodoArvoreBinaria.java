package projetoed.estruturas.arvorebinaria.interfaces;

import projetoed.estruturas.listanodos.interfaces.INodo;

public interface INodoArvoreBinaria<T> extends INodo<T> {
    T element();
    void setElement(T elemento);
    INodoArvoreBinaria<T> getLeft();
    void setLeft(INodoArvoreBinaria<T> nodo);
    INodoArvoreBinaria<T> getRight();
    void setRight(INodoArvoreBinaria<T> nodo);
    INodoArvoreBinaria<T> getParent();
    void setParent(INodoArvoreBinaria<T> nodo);
}
