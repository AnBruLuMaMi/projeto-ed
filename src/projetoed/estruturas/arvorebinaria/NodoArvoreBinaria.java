package projetoed.estruturas.arvorebinaria;

import projetoed.estruturas.arvorebinaria.interfaces.INodoArvoreBinaria;

public class NodoArvoreBinaria<T> implements INodoArvoreBinaria<T> {
    private T elemento;
    private INodoArvoreBinaria<T> esquerda, direita, pai;

    public NodoArvoreBinaria() {
    }

    public NodoArvoreBinaria(T elemento, INodoArvoreBinaria<T> pai) {
        setElement(elemento);
        setParent(pai);
    }

    public NodoArvoreBinaria(T elemento, INodoArvoreBinaria<T> pai, INodoArvoreBinaria<T> esquerda, INodoArvoreBinaria<T> direita) {
        setElement(elemento);
        setParent(pai);
        setLeft(esquerda);
        setRight(direita);
    }

    @Override
    public T element() {
        return elemento;
    }

    @Override
    public void setElement(T elemento) {
        this.elemento = elemento;
    }

    @Override
    public INodoArvoreBinaria<T> getLeft() {
        return esquerda;
    }

    @Override
    public void setLeft(INodoArvoreBinaria<T> nodo) {
        this.esquerda = nodo;
    }

    @Override
    public INodoArvoreBinaria<T> getRight() {
        return direita;
    }

    @Override
    public void setRight(INodoArvoreBinaria<T> nodo) {
        this.direita = nodo;
    }

    @Override
    public INodoArvoreBinaria<T> getParent() {
        return pai;
    }

    @Override
    public void setParent(INodoArvoreBinaria<T> nodo) {
        this.pai = nodo;
    }

    @Override
    public String toString() {
        return elemento.toString();
    }
}
