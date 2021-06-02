package projetoed.estruturas.listaarranjo.test;

import org.junit.jupiter.api.Test;
import projetoed.estruturas.listaarranjo.ListaArranjo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListaArranjoTest {
    @Test
    void test() {
        ListaArranjo<Integer> listaArranjo = new ListaArranjo();
        assertEquals("[]", listaArranjo.toString());

        assertEquals(true, listaArranjo.isEmpty());

        assertEquals(0, listaArranjo.size());

        listaArranjo.add(0, 7);
        assertEquals("[7]", listaArranjo.toString());

        listaArranjo.add(0, 4);
        assertEquals("[4,7]", listaArranjo.toString());

        assertEquals(7, listaArranjo.get(1));

        listaArranjo.add(2, 2);
        assertEquals("[4,7,2]", listaArranjo.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> { listaArranjo.get(3); });

        assertEquals(7, listaArranjo.remove(1));

        listaArranjo.add(1, 5);
        assertEquals("[4,5,2]", listaArranjo.toString());

        assertEquals(false, listaArranjo.isEmpty());

        listaArranjo.add(1, 3);
        assertEquals("[4,3,5,2]", listaArranjo.toString());

        listaArranjo.add(4, 9);
        assertEquals("[4,3,5,2,9]", listaArranjo.toString());

        assertEquals(5, listaArranjo.size());

        assertEquals(5, listaArranjo.get(2));

        assertEquals(2, listaArranjo.set(3, 8));
    }
}
