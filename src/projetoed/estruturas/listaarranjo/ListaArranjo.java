package projetoed.estruturas.listaarranjo;


import projetoed.estruturas.listaarranjo.interfaces.IListaArranjo;

public class ListaArranjo<T> implements IListaArranjo<T> {
    private T[] arranjo;
    private int capacidadeMaxima;
    private int quantidadeElementosArmazenados;

    public ListaArranjo() {
        capacidadeMaxima = 10;
        arranjo = (T[])new Object[capacidadeMaxima];
        quantidadeElementosArmazenados = 0;
    }

    private void validarPosicaoArranjo(int i, int n) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T get(int i) {
        validarPosicaoArranjo(i, size());
        return arranjo[i];
    }

    @Override
    public T set(int i, T e) {
        validarPosicaoArranjo(i, size());
        T temp = arranjo[i];
        arranjo[i] = e;

        return temp;
    }

    @Override
    public void add(int i, T e) {
        validarPosicaoArranjo(i, size() + 1);

        if (quantidadeElementosArmazenados == capacidadeMaxima) {
            capacidadeMaxima *= 2;
            T[] arranjoTemporario = (T[])new Object[capacidadeMaxima];

            for (int j = 0; j < quantidadeElementosArmazenados; j++) {
                arranjoTemporario[j] = arranjo[j];
            }

            arranjo = arranjoTemporario;
        }

        for (int j = quantidadeElementosArmazenados - 1; j >= i; j--) {
            arranjo[j + 1] = arranjo[j];
        }

        arranjo[i] = e;
        quantidadeElementosArmazenados++;
    }

    @Override
    public T remove(int i) {
        validarPosicaoArranjo(i, size());
        T temp = arranjo[i];

        for (int j = i; j < size() - 1; j++) {
            arranjo[j] = arranjo[j + 1];
        }

        quantidadeElementosArmazenados--;

        return temp;
    }

    @Override
    public int size() {
        return quantidadeElementosArmazenados;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        String arranjoString = "[";

        for (int i = 0; i < size(); i++) {
            arranjoString += arranjo[i].toString();

            if (i < size() - 1) {
                arranjoString += ",";
            }
        }

        arranjoString += "]";

        return arranjoString;
    }
}
