package projetoed.estruturas.grafo;

import java.util.ArrayList;

public class Aresta<T> {
    private T elemento;
    private ArrayList<Vertice<T>> endVertices;
    private Aresta<T> instancia;

    public Aresta(Vertice<T> vertice1, Vertice<T> vertice2, T elemento) {
        this.endVertices = new ArrayList<>();
        this.endVertices.add(vertice1);
        this.endVertices.add(vertice2);
        this.elemento =  elemento;
        this.instancia = this;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public ArrayList<Vertice<T>> getEndVertices() {
        return endVertices;
    }

    public Aresta<T> getInstancia() {
        return instancia;
    }

    boolean isIncident(Vertice<T> vertice) {
        return endVertices.get(0) == vertice || endVertices.get(1) == vertice;
    }

    @Override
    public String toString() {
        return endVertices.get(0) + "--" + elemento + "--" + endVertices.get(1);
    }
}
