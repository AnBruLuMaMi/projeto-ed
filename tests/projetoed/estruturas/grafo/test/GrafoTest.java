package projetoed.estruturas.grafo.test;

import org.junit.jupiter.api.Test;
import projetoed.estruturas.grafo.Grafo;
import projetoed.estruturas.grafo.exception.ArestaExisteException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GrafoTest {
    @Test
    void test() {
        var grafo = new Grafo<String>();

        var vertex1 = grafo.insertVertex("A");
        var vertex2 = grafo.insertVertex("B");
        var vertex3 = grafo.insertVertex("C");
        var vertex4 = grafo.insertVertex("D");

        assertEquals("Vertices: A B C D", grafo.toString());

        var aresta1 = grafo.insertEdge(vertex1, vertex2, "x");
        assertEquals("Vertices: A B C D\nArestas:\nA--x--B", grafo.toString());

        assertThrows(ArestaExisteException.class, () -> grafo.insertEdge(vertex1, vertex2, "teste"));

        grafo.insertEdge(vertex2, vertex3, "y");
        assertEquals("Vertices: A B C D\nArestas:\nA--x--B\nB--y--C", grafo.toString());

        var aresta3 = grafo.insertEdge(vertex3, vertex4, "z");
        assertEquals("Vertices: A B C D\nArestas:\nA--x--B\nB--y--C\nC--z--D", grafo.toString());

        assertEquals("[A, B, C, D]", grafo.vertices().toString());
        assertEquals("[A--x--B, B--y--C, C--z--D]", grafo.edges().toString());

        assertEquals("[A--x--B, B--y--C]", grafo.incidentEdges(vertex2).toString());
        assertEquals("A", grafo.opposite(vertex2, aresta1).toString());
        assertEquals("[A, B]", grafo.endVertices(aresta1).toString());
        assertEquals(true, grafo.areAdjacent(vertex1, vertex2));
        assertEquals(false, grafo.areAdjacent(vertex1, vertex3));
        assertEquals(true, grafo.areAdjacent(vertex3, vertex4));

        grafo.replace(vertex2, "E");
        assertEquals("[A, E, C, D]", grafo.vertices().toString());

        grafo.replace(aresta1, "w");
        assertEquals("[A--w--E, E--y--C, C--z--D]", grafo.edges().toString());

        assertEquals("E", grafo.removeVertex(vertex2));
        assertEquals("[A, C, D]", grafo.vertices().toString());
        assertEquals("[C--z--D]", grafo.edges().toString());

        assertEquals("z", grafo.removeEdge(aresta3));
        assertEquals("[]", grafo.edges().toString());
    }
}
