package projetoed.estruturas.listanodos.test;

import org.junit.jupiter.api.Test;
import projetoed.estruturas.listanodos.ListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListaNodoTest {
    private <T> ListaNodo<T> inverterListaNodos(ListaNodo<T> lista) {
        ListaNodo<T> listaNova = new ListaNodo<>();

        INodo<T> elementoAtual = lista.last();
        INodo<T> primeiroElemento = lista.first();

        listaNova.addFirst(elementoAtual.element());

        while (elementoAtual != primeiroElemento) {
            elementoAtual = lista.prev(elementoAtual);
            listaNova.addAfter(listaNova.last(), elementoAtual.element());
        }

        return listaNova;
    }

    @Test
    void test() {
        ListaNodo<Integer> l = new ListaNodo();

        assertEquals("[]", l.toString(), "[]");

        l.addFirst(8);
        l.addAfter(l.last(), 2);
        l.addAfter(l.last(), 7);
        l.addAfter(l.last(), 5);

        assertEquals("[8, 2, 7, 5]", l.toString(), "[8, 2, 7, 5]");

        assertEquals("[5, 7, 2, 8]", inverterListaNodos(l).toString());
    }
}
