package projetoed.estruturas.grafo.interfaces;

import projetoed.estruturas.listanodos.interfaces.IListaNodo;

public interface IGrafo<T> {
    IListaNodo<T> vertices();
    IListaNodo<T> edges();
}
