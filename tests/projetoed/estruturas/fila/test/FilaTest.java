package projetoed.estruturas.fila.test;

import org.junit.jupiter.api.Test;
import projetoed.estruturas.fila.FilaLSE;
import projetoed.estruturas.fila.exceptions.EmptyQueueException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilaTest {
    static final FilaLSE<Integer> fila = new FilaLSE<Integer>();

    @Test
    void test() throws EmptyQueueException {
        assertEquals("[]", fila.toString(), "Fila vazia");

        fila.enqueue(1);

        assertEquals("[1]", fila.toString(), "Deve resultar [1]");

        fila.enqueue(2);

        assertEquals("[1, 2]", fila.toString(), "Deve resultar [1, 2]");

        assertEquals(1, fila.dequeue(), "Deve resultar 1");

        assertEquals("[2]", fila.toString(), "Deve resultar [2]");

        assertEquals(2, fila.dequeue(), "Deve resultar 2");

        assertEquals("[]", fila.toString(), "Deve resultar []");

        assertThrows(EmptyQueueException.class, () -> { fila.dequeue(); });
    }
}
