package projetoed.estruturas.grafo;

public class Vertice<T> {
    private T elemento;
    private Vertice<T> instancia;

    public Vertice(T elemento) {
        this.elemento = elemento;
        this.instancia = this;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public Vertice<T> getInstancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "" + elemento;
    }
}
