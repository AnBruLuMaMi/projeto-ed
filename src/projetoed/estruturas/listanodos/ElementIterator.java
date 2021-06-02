package projetoed.estruturas.listanodos;

import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElementIterator<T> implements Iterator<T> {
    private IListaNodo<T> lista;
    private INodo<T> cursor;

    public ElementIterator(IListaNodo<T> lista) {
        this.lista = lista;
        cursor = (this.lista.isEmpty()) ? null : this.lista.first();
    }

    @Override
    public boolean hasNext() {
        return (cursor != null);
    }

    @Override
    public T next() {
        if (cursor == null)
            throw new NoSuchElementException("Não há próximo elemento");

        T toReturn = cursor.element();

        cursor = (cursor == lista.last()) ? null : lista.next(cursor);

        return toReturn;
    }
}
