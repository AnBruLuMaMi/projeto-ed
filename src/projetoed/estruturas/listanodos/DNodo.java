package projetoed.estruturas.listanodos;

import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

public class DNodo<T> implements INodo<T> {
    private IListaNodo<T> lista;
    private DNodo<T> anterior, proximo;
    private T elemento;

    public DNodo(DNodo<T> anterior, DNodo<T> proximo, T elemento, IListaNodo<T> lista) {
        this.anterior = anterior;
        this.proximo = proximo;
        this.elemento = elemento;
        this.lista = lista;
    }

    @Override
    public T element() throws NodoInvalidoException {
        if ((anterior == null) && (proximo == null))
            throw new NodoInvalidoException("O nodo não está em uma lista!");

        return elemento;
    }

    public DNodo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(DNodo<T> anterior) {
        this.anterior = anterior;
    }

    public DNodo<T> getProximo() {
        return proximo;
    }

    public void setProximo(DNodo<T> proximo) {
        this.proximo = proximo;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public IListaNodo<T> getLista() {
        return lista;
    }
}


