package projetoed.estruturas.filaprioridade;

import projetoed.estruturas.arvoregenerica.exceptions.ArvoreVaziaException;
import projetoed.estruturas.filaprioridade.interfaces.IArvoreBinariaCompleta;
import projetoed.estruturas.listanodos.ListaNodo;
import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

import java.util.ArrayList;
import java.util.Iterator;

public class ArvoreBinariaCompleta<T> implements IArvoreBinariaCompleta<T> {
    private ArrayList<NodoArvoreBinaria<T>> arvore;

    private static class NodoArvoreBinaria<T> implements INodo<T> {
        private T elemento;
        private int indice;

        public NodoArvoreBinaria(T elemento, int i) {
            this.elemento = elemento;
            this.indice = i;
        }

        public T element() {
            return elemento;
        }

        public int index() {
            return indice;
        }

        public T setElement(T elt) {
            T temp = elemento;
            elemento = elt;

            return temp;
        }

        public String toString() {
            return ("[" + elemento + "," + indice + "]");
        }
    }

    public ArvoreBinariaCompleta() {
        arvore = new ArrayList<NodoArvoreBinaria<T>>();
        arvore.add(0, null);
    }

    private NodoArvoreBinaria<T> validarNodo(INodo<T> nodo) throws NodoInvalidoException {
        if (nodo == null || !(nodo instanceof NodoArvoreBinaria))
            throw new NodoInvalidoException("Nodo inválido.");

        return (NodoArvoreBinaria<T>) nodo;
    }

    @Override
    public Iterable<INodo<T>> positions() {
        ArrayList<INodo<T>> nodos = new ArrayList<INodo<T>>();
        Iterator<NodoArvoreBinaria<T>> iterador = arvore.iterator();

        iterador.next();

        while (iterador.hasNext())
            nodos.add(iterador.next());

        return nodos;
    }

    @Override
    public T replace(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        NodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);
        return nodoArvoreBinaria.setElement(elemento);
    }

    @Override
    public INodo<T> root() throws ArvoreVaziaException {
        if (isEmpty())
            throw new ArvoreVaziaException("A arvore está vazia.");

        return arvore.get(1);
    }

    @Override
    public INodo<T> parent(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        if (isRoot(nodo))
            throw new LimiteVioladoException("Sem parente");

        NodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        return arvore.get(nodoArvoreBinaria.index() / 2);
    }

    @Override
    public Iterable<INodo<T>> children(INodo<T> nodo) throws NodoInvalidoException {
        IListaNodo<INodo<T>> filhos = new ListaNodo();

        if (hasLeft(nodo)) filhos.addLast(left(nodo));

        if (hasRight(nodo)) filhos.addLast(right(nodo));

        return filhos;
    }

    @Override
    public boolean isInternal(INodo<T> nodo) throws NodoInvalidoException {
        return hasLeft(nodo);
    }

    @Override
    public boolean isExternal(INodo<T> nodo) throws NodoInvalidoException {
        return !isInternal(nodo);
    }

    @Override
    public boolean isRoot(INodo<T> nodo) throws NodoInvalidoException {
        NodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);
        return nodoArvoreBinaria.index() == 1;
    }

    @Override
    public Iterator<T> iterator() {
        ArrayList<T> lista = new ArrayList<T>();
        Iterator<NodoArvoreBinaria<T>> iterador = arvore.iterator();

        iterador.next();

        while (iterador.hasNext())
            lista.add(iterador.next().element());

        return lista.iterator();
    }

    @Override
    public int size() {
        return arvore.size() - 1;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public INodo<T> left(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        if (!hasLeft(nodo))
            throw new LimiteVioladoException("Sem filho na esquerda");

        NodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);
        return arvore.get(2 * nodoArvoreBinaria.index());
    }

    @Override
    public INodo<T> right(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        if (!hasRight(nodo))
            throw new LimiteVioladoException("Sem filho na direita");

        NodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        return arvore.get(2 * nodoArvoreBinaria.index() + 1);
    }

    @Override
    public boolean hasLeft(INodo<T> nodo) throws NodoInvalidoException {
        NodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);
        return 2 * nodoArvoreBinaria.index() <= size();
    }

    @Override
    public boolean hasRight(INodo<T> nodo) throws NodoInvalidoException {
        NodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);
        return 2 * nodoArvoreBinaria.index() + 1 <= size();
    }

    @Override
    public INodo<T> add(T elem) {
        int i = size() + 1;

        NodoArvoreBinaria<T> p = new NodoArvoreBinaria<T>(elem, i);
        arvore.add(i, p);

        return p;
    }

    @Override
    public T remove() {
        if (isEmpty()) throw new ArvoreVaziaException("A árvore está vazia.");
        return arvore.remove(size()).element();
    }

    public String toString() {
        return arvore.toString();
    }

    private String preorder(NodoArvoreBinaria<T> nodo, String s) {
        s += nodo.indice + ", ";

        if (hasLeft(nodo)) {
            s = preorder((NodoArvoreBinaria<T>) left(nodo), s);
        }

        if (hasRight(nodo)) {
            s = preorder((NodoArvoreBinaria<T>) right(nodo), s);
        }

        return s;
    }

    private String preorder2(NodoArvoreBinaria<T> nodo, String s) {
        s += nodo.elemento + ", ";

        if (hasLeft(nodo)) {
            s = preorder2((NodoArvoreBinaria<T>) left(nodo), s);
        }

        if (hasRight(nodo)) {
            s = preorder2((NodoArvoreBinaria<T>) right(nodo), s);
        }

        return s;
    }

    private String inorder(NodoArvoreBinaria<T> nodo, String s) {
        if (hasLeft(nodo)) {
            s = inorder((NodoArvoreBinaria<T>) left(nodo), s);
        }

        s += nodo.indice + ", ";

        if (hasRight(nodo)) {
            s = inorder((NodoArvoreBinaria<T>) right(nodo), s);
        }

        return s;
    }

    private String postorder(NodoArvoreBinaria<T> nodo, String s) {
        if (hasLeft(nodo)) {
            s = postorder((NodoArvoreBinaria<T>) left(nodo), s);
        }

        if (hasRight(nodo)) {
            s = postorder((NodoArvoreBinaria<T>) right(nodo), s);
        }

        s += nodo.indice + ", ";

        return s;
    }

    private String postorder2(NodoArvoreBinaria<T> nodo, String s) {
        if (hasLeft(nodo)) {
            s = postorder2((NodoArvoreBinaria<T>) left(nodo), s);
        }

        if (hasRight(nodo)) {
            s = postorder2((NodoArvoreBinaria<T>) right(nodo), s);
        }

        s += nodo.elemento + ", ";

        return s;
    }

    public String toStringPreOrder() {
        String s = "";
        s = preorder((NodoArvoreBinaria<T>) root(), s);

        return "[" + s.substring(0, s.length() - 2) + "]";
    }

    public String toStringPreOrder2() {
        String s = "";
        s = preorder2((NodoArvoreBinaria<T>) root(), s);

        return "[" + s.substring(0, s.length() - 2) + "]";
    }

    public String toStringInOrder() {
        String s = "";
        s = inorder((NodoArvoreBinaria<T>) root(), s);

        return "[" + s.substring(0, s.length() - 2) + "]";
    }

    public String toStringPostOrder() {
        String s = "";
        s = postorder((NodoArvoreBinaria<T>) root(), s);

        return "[" + s.substring(0, s.length() - 2) + "]";
    }

    public String toStringPostOrder2() {
        String s = "";
        s = postorder2((NodoArvoreBinaria<T>) root(), s);

        return "[" + s.substring(0, s.length() - 2) + "]";
    }
}
