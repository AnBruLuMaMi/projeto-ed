package projetoed.estruturas.dicionario.test;

import org.junit.jupiter.api.Test;
import projetoed.estruturas.dicionario.Dicionario;
import projetoed.estruturas.dicionario.interfaces.IDicionario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DicionarioTest {
    @Test
    void test() {
        IDicionario<Integer, String> dict = new Dicionario<>();

        assertEquals("5=A", dict.put(5, "A").toString());
        assertEquals("[5=A]", dict.entrySet().toString());
        assertEquals("7=B", dict.put(7, "B").toString());
        assertEquals("[5=A, 7=B]", dict.entrySet().toString());
        assertEquals("2=C", dict.put(2, "C").toString());
        assertEquals("[2=C, 5=A, 7=B]", dict.entrySet().toString());
        assertEquals("8=D", dict.put(8, "D").toString());
        assertEquals("[2=C, 5=A, 7=B, 8=D]", dict.entrySet().toString());
        assertEquals("2=E", dict.put(2, "E").toString());
        assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());
        assertEquals("7=B", dict.get(7).toString());
        assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());
        assertNull(dict.get(4));
        assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());
        assertEquals("2=C", dict.get(2).toString());
        assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());
        assertEquals("[2=C, 2=E]", dict.getAll(2).toString());
        assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());
        assertEquals(5, dict.size());
        assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());
        assertEquals("5=A", dict.remove(dict.get(5)).toString());
        assertEquals("[2=C, 2=E, 7=B, 8=D]", dict.entrySet().toString());
        assertEquals("2=C", dict.remove(dict.get(2)).toString());
        assertEquals("[2=E, 7=B, 8=D]", dict.entrySet().toString());
    }
}
