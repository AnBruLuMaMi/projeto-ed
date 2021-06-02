package projetoed.estruturas.grafo;

import projetoed.estruturas.grafo.exception.ArestaExisteException;
import projetoed.estruturas.listanodos.ListaNodo;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Grafo<T> {
    private LinkedList<Vertice<T>> vertices;
    private LinkedList<Aresta<T>> arestas;

    public Grafo() {
        vertices = new LinkedList<>();
        arestas = new LinkedList<>();
    }

    public Iterable<Vertice<T>> vertices() {
        return vertices;
    }

    public Iterable<Aresta<T>> edges() {
        return arestas;
    }

    public IListaNodo<Aresta<T>> incidentEdges(Vertice<T> vertice) {
        var arestasIncidentes = new ListaNodo<Aresta<T>>();

        for (var arestai:
             arestas) {
            if (arestai.isIncident(vertice)) {
                arestasIncidentes.addLast(arestai);
            }
        }

        return arestasIncidentes;
    }

    public Vertice<T> opposite(Vertice<T> vertice, Aresta<T> aresta) {
        if (!aresta.isIncident(vertice))
            throw new NodoInvalidoException("A vertice deve ser incidente");

        var endVertices = aresta.getEndVertices();
        var primeiroVertice = endVertices.get(0);

        if (primeiroVertice != vertice) {
            return primeiroVertice;
        } else {
            return endVertices.get(1);
        }
    }

    public ArrayList<Vertice<T>> endVertices(Aresta<T> aresta) {
        return aresta.getEndVertices();
    }

    public boolean areAdjacent(Vertice<T> vertice1, Vertice<T> vertice2) {
        for (var arestai:
                arestas) {
            if (arestai.isIncident(vertice1) && arestai.isIncident(vertice2)) {
                return true;
            }
        }

        return false;
    }

    public void replace(Vertice<T> vertice, T elemento) {
        vertice.setElemento(elemento);
    }

    public void replace(Aresta<T> aresta, T elemento) {
        aresta.setElemento(elemento);
    }

    public Vertice<T> insertVertex(T elemento) {
        var nodo = new Vertice<>(elemento);
        vertices.addLast(nodo);

        return nodo;
    }

    public Aresta<T> insertEdge(Vertice<T> vertice1, Vertice<T> vertice2, T elemento) {
        var nodo = new Aresta<>(vertice1, vertice2, elemento);

        if (areAdjacent(vertice1, vertice2)) {
            throw new ArestaExisteException("Já existe uma aresta ligando os dois vertices");
        }

        arestas.addLast(nodo);
        return nodo;
    }

    public T removeVertex(Vertice<T> vertice) {
        T elemento = vertice.getElemento();

        var arestasIncidentes = incidentEdges(vertice);

        for (var aresta:
             arestasIncidentes) {
            arestas.remove(aresta.getInstancia());
        }

        vertices.remove(vertice.getInstancia());

        return elemento;
    }

    public T removeEdge(Aresta<T> aresta) {
        T elemento = aresta.getElemento();
        arestas.remove(aresta.getInstancia());

        return elemento;
    }

    public String gerarStringVertices() {
        String s = "Vertices: ";

        for (var vertice:
                vertices) {
            s += vertice + " ";
        }

        s = s.substring(0, s.length() - 1);

        return s;
    }

    public String gerarStringArestas() {
        String s = "";

        if (vertices.size() > 0)
            s += "\n";

        s += "Arestas:\n";

        for (var aresta:
                arestas) {
            s += aresta + "\n";
        }

        s = s.substring(0, s.length() - 1);

        return s;
    }

    @Override
    public String toString() {
        String s = "";

        if (vertices.size() > 0)
            s += gerarStringVertices();

        if (arestas.size() > 0)
            s += gerarStringArestas();

        return s;
    }
}
