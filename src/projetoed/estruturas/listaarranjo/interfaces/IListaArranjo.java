package projetoed.estruturas.listaarranjo.interfaces;

public interface IListaArranjo<T> {
    T get(int i) throws IndexOutOfBoundsException;
    T set(int i, T e) throws IndexOutOfBoundsException;
    void add(int i, T e);
    T remove(int i);
    int size();
    boolean isEmpty();
}
