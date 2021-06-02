package projetoed.estruturas.arvoregenerica.interfaces;

import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

public interface INodoArvore<T> extends INodo<T> {
    void setElement(T elemento);
    T getElement();
    IListaNodo<INodo<T>> getChildren();
    void setChildren(IListaNodo<INodo<T>> nodesFilhos);
    INodoArvore<T> getParent();
    void setParent(INodoArvore<T> nodo);
}
