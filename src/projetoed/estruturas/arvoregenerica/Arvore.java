package projetoed.estruturas.arvoregenerica;

import projetoed.estruturas.arvoregenerica.exceptions.ArvoreNaoVaziaException;
import projetoed.estruturas.arvoregenerica.exceptions.ArvoreVaziaException;
import projetoed.estruturas.arvoregenerica.interfaces.IArvore;
import projetoed.estruturas.arvoregenerica.interfaces.INodoArvore;
import projetoed.estruturas.listanodos.ListaNodo;
import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

import java.util.Iterator;

public class Arvore<T> implements IArvore<T> {
    private INodoArvore<T> raiz;
    private int quantidadeNodosArvore;

    public Arvore() {
        raiz = null;
        quantidadeNodosArvore = 0;
    }

    private void preOrdemNodos(INodo<T> nodo, IListaNodo<INodo<T>> listaNodo) {
        listaNodo.addLast(nodo);

        for (INodo<T> nodoFilho : children(nodo))
            preOrdemNodos(nodoFilho, listaNodo);
    }

    private INodoArvore<T> criarNodo(T elemento, INodoArvore<T> pai, IListaNodo<INodo<T>> filhos) {
        return new NodoArvore(elemento, pai, filhos);
    }

    private static <T> String toString(Arvore<T> arvore) {
        String s = "";

        for (T i : arvore) {
            s += ", " + i;
        }

        s = (s.length() == 0 ? s : s.substring(2));

        return "[" + s + "]";
    }

    protected INodoArvore<T> validarNodo(INodo<T> nodo) {
        if (nodo == null || !(nodo instanceof INodoArvore))
            throw new NodoInvalidoException("O nodo é inválido!");

        return (INodoArvore<T>)nodo;
    }

    public INodoArvore<T> addRoot(T elemento) throws ArvoreNaoVaziaException {
        if (!isEmpty())
            throw new ArvoreNaoVaziaException("A árvore já possui uma raiz.");

        quantidadeNodosArvore = 1;
        raiz = criarNodo(elemento, null, new ListaNodo<>());

        return raiz;
    }

    public INodoArvore<T> addChild(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        INodoArvore<T> nodoArvore = validarNodo(nodo);

        IListaNodo<INodo<T>> filhos = nodoArvore.getChildren();
        INodoArvore<T> nodoNovo = criarNodo(elemento, nodoArvore, new ListaNodo<>());

        filhos.addLast(nodoNovo);

        quantidadeNodosArvore += 1;

        return nodoNovo;
    }

    public void swapElements(INodo<T> nodo, INodo<T> nodo2) throws NodoInvalidoException {
        INodoArvore<T> nodoArvore = validarNodo(nodo);
        INodoArvore<T> nodoArvore2 = validarNodo(nodo2);

        T temp = nodo2.element();
        nodoArvore2.setElement(nodo.element());
        nodoArvore.setElement(temp);
    }

    @Override
    public Iterable<INodo<T>> positions() {
        IListaNodo<INodo<T>> listaNodo = new ListaNodo();

        if (size() != 0)
            preOrdemNodos(root(), listaNodo);

        return listaNodo;
    }

    @Override
    public T replace(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        INodoArvore<T> nodoArvore = validarNodo(nodo);

        T temp = nodo.element();
        nodoArvore.setElement(elemento);

        return temp;
    }

    @Override
    public INodoArvore<T> root() throws ArvoreVaziaException {
        if (raiz == null)
            throw new ArvoreVaziaException("A árvore está vazia.");

        return raiz;
    }

    @Override
    public INodoArvore<T> parent(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        INodoArvore<T> nodoArvore = validarNodo(nodo);

        INodoArvore<T> nodoPai = nodoArvore.getParent();

        if (nodoPai == null)
            throw new LimiteVioladoException("O nodo não possui pai.");

        return nodoPai;
    }

    @Override
    public Iterable<INodo<T>> children(INodo<T> nodo) throws NodoInvalidoException {
        INodoArvore<T> nodoArvore = validarNodo(nodo);

        return nodoArvore.getChildren();
    }

    @Override
    public boolean isInternal(INodo<T> nodo) throws NodoInvalidoException {
        return !isExternal(nodo);
    }

    @Override
    public boolean isExternal(INodo<T> nodo) throws NodoInvalidoException {
        INodoArvore<T> nodoArvore = validarNodo(nodo);
        return nodoArvore.getChildren() == null || nodoArvore.getChildren().isEmpty();
    }

    @Override
    public boolean isRoot(INodo<T> nodo) throws NodoInvalidoException {
        validarNodo(nodo);

        return nodo == root();
    }

    @Override
    public Iterator<T> iterator() {
        Iterable<INodo<T>> nodos = positions();

        IListaNodo<T> listaNodo = new ListaNodo();

        for (INodo<T> nodo : nodos)
            listaNodo.addLast(nodo.element());

        return listaNodo.iterator();
    }

    @Override
    public int size() {
        return quantidadeNodosArvore;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return toString(this);
    }
}
