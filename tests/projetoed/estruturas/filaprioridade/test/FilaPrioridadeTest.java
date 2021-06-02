package projetoed.estruturas.filaprioridade.test;

import org.junit.jupiter.api.Test;
import projetoed.estruturas.filaprioridade.FilaPrioridadeHeap;
import projetoed.estruturas.filaprioridade.interfaces.IFilaPrioridade;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilaPrioridadeTest {
    @Test
    void test() {
        IFilaPrioridade<Integer, String> filaPrioridadeH = new FilaPrioridadeHeap<>();

        filaPrioridadeH.insert(4, "C");
        filaPrioridadeH.insert(5, "A");
        filaPrioridadeH.insert(6, "Z");
        filaPrioridadeH.insert(15, "K");
        filaPrioridadeH.insert(9, "F");
        filaPrioridadeH.insert(7, "Q");
        filaPrioridadeH.insert(20, "B");
        filaPrioridadeH.insert(16, "X");
        filaPrioridadeH.insert(25, "J");
        filaPrioridadeH.insert(14, "E");
        filaPrioridadeH.insert(12, "H");
        filaPrioridadeH.insert(11, "S");
        filaPrioridadeH.insert(8, "W");

        assertEquals("[null, [(4,C),1], [(5,A),2], [(6,Z),3], [(15,K),4], [(9,F),5], "
                + "[(7,Q),6], [(20,B),7], [(16,X),8], [(25,J),9], [(14,E),10], [(12,H),11], "
                + "[(11,S),12], [(8,W),13]]", filaPrioridadeH.toString());

        filaPrioridadeH.insert(2, "T");

        assertEquals("[null, [(2,T),1], [(5,A),2], [(4,C),3], [(15,K),4], [(9,F),5], "
                + "[(7,Q),6], [(6,Z),7], [(16,X),8], [(25,J),9], [(14,E),10], [(12,H),11], "
                + "[(11,S),12], [(8,W),13], [(20,B),14]]", filaPrioridadeH.toString());

        filaPrioridadeH.removeMin();

        assertEquals(
                "[null, [(4,C),1], [(5,A),2], [(6,Z),3], [(15,K),4], [(9,F),5], [(7,Q),6], [(20,B),7],"
                        + " [(16,X),8], [(25,J),9], [(14,E),10], [(12,H),11], [(11,S),12], [(8,W),13]]", filaPrioridadeH.toString());
    }
}
