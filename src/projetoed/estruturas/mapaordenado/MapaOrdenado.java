package projetoed.estruturas.mapaordenado;

import projetoed.estruturas.arvorebinaria.ArvoreBinaria;
import projetoed.estruturas.arvorebinaria.interfaces.INodoArvoreBinaria;
import projetoed.estruturas.filaprioridade.DefaultComparator;
import projetoed.estruturas.filaprioridade.exceptions.ChaveInvalidaException;
import projetoed.estruturas.filaprioridade.interfaces.IEntry;
import projetoed.estruturas.listanodos.ListaNodo;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;
import projetoed.estruturas.mapa.interfaces.IMapa;

import java.util.Comparator;

public class MapaOrdenado<K,V> extends ArvoreBinaria<IEntry<K, V>> implements IMapa<K,V> {
    protected Comparator<K> C;
    protected INodo<IEntry<K, V>> actionPos;
    protected int qtdElementos = 0;

    public MapaOrdenado() {
        C = new DefaultComparator<K>();
        addRoot(null);
    }

    public MapaOrdenado(Comparator<K> comparador) {
        C = comparador;
        addRoot(null);
    }

    protected K key(INodo<IEntry<K, V>> position) {
        return position.element().getKey();
    }

    protected V value(INodo<IEntry<K, V>> nodo) {
        return nodo.element().getValue();
    }

    protected IEntry<K, V> entry(INodo<IEntry<K, V>> nodo) {
        return nodo.element();
    }

    protected void validarChave(K chave) throws ChaveInvalidaException {
        if (chave == null)
            throw new ChaveInvalidaException("Chave nula");
    }

    protected INodo<IEntry<K, V>> treeSearch(K chave, INodo<IEntry<K, V>> nodo) {
        if (isExternal(nodo))
            return nodo;
        else {
            K curKey = key(nodo);
            int comp = C.compare(chave, curKey);

            if (comp < 0)
                return treeSearch(chave, left(nodo));
            else if (comp > 0)
                return treeSearch(chave, right(nodo));

            return nodo;
        }
    }

    protected IEntry<K, V> insertAtExternal(INodo<IEntry<K, V>> nodo, IEntry<K, V> entry) {
        expandExternal(nodo, null, null);
        replace(nodo, entry);

        qtdElementos++;

        return entry;
    }

    protected V replaceEntry(INodo<IEntry<K, V>> nodo, IEntry<K, V> entry) {
        ((ArvoreBinariaEntry<K, V>) entry).nodo = nodo;

        return replace(nodo, entry).getValue();
    }

    protected void removeExternal(INodo<IEntry<K, V>> nodo) {
        removeAboveExternal(nodo);
        qtdElementos--;
    }

    @Override
    public V put(K chave, V valor) throws ChaveInvalidaException {
        validarChave(chave);

        INodo<IEntry<K, V>> insPos = treeSearch(chave, root());
        ArvoreBinariaEntry<K, V> entry = new ArvoreBinariaEntry(chave, valor, insPos);

        actionPos = insPos;

        if (isExternal(insPos)) {
            insertAtExternal(insPos, entry);
            return null;
        }

        return replaceEntry(insPos, entry);
    }

    @Override
    public V get(K chave) throws ChaveInvalidaException {
        validarChave(chave);

        INodo<IEntry<K, V>> curPos = treeSearch(chave, root());

        actionPos = curPos;

        if (isInternal(curPos))
            return value(curPos);

        return null;
    }

    @Override
    public V remove(K chave) throws ChaveInvalidaException {
        validarChave(chave);

        INodo<IEntry<K, V>> remPos = treeSearch(chave, root());

        if (isExternal(remPos))
            return null;

        IEntry<K, V> toReturn = entry(remPos);

        if (isExternal(left(remPos)))
            remPos = left(remPos);
        else if (isExternal(right(remPos)))
            remPos = right(remPos);
        else {
            INodo<IEntry<K, V>> swapPos = remPos;

            remPos = right(swapPos);

            do
                remPos = left(remPos);
            while (isInternal(remPos));

            replaceEntry(swapPos, (IEntry<K, V>) parent(remPos).element());
        }

        actionPos = sibling(remPos);

        removeExternal(remPos);

        return toReturn.getValue();
    }

    @Override
    public Iterable<K> keySet() {
        IListaNodo<K> keys = new ListaNodo();

        Iterable<INodo<IEntry<K, V>>> iteradorNodo = positionsInorder();

        for (INodo<IEntry<K, V>> cur : iteradorNodo)
            if (isInternal(cur))
                keys.addLast(key(cur));

        return keys;
    }

    @Override
    public Iterable<V> values() {
        IListaNodo<V> vals = new ListaNodo<V>();

        Iterable<INodo<IEntry<K, V>>> iteradorNodo = positionsInorder();

        for (INodo<IEntry<K, V>> cur : iteradorNodo)
            if (isInternal(cur))
                vals.addLast(value(cur));

        return vals;
    }

    @Override
    public Iterable<IEntry<K, V>> entrySet() {
        IListaNodo<IEntry<K, V>> entries = new ListaNodo();

        Iterable<INodo<IEntry<K, V>>> iteradorNodo = positionsInorder();

        for (INodo<IEntry<K, V>> cur : iteradorNodo)
            if (isInternal(cur))
                entries.addLast(cur.element());

        return entries;
    }

    public void expandExternal(INodo<IEntry<K, V>> nodo, IEntry<K, V> l, IEntry<K, V> r) throws NodoInvalidoException {
        if (!isExternal(nodo))
            throw new NodoInvalidoException("O Nodo não é externo");

        insertLeft(nodo, l);
        insertRight(nodo, r);
    }

    public void removeAboveExternal(INodo<IEntry<K, V>> nodo) throws NodoInvalidoException {
        if (!isExternal(nodo))
            throw new NodoInvalidoException("O Nodo não é externo");

        if (isRoot(nodo))
            remove(nodo);
        else {
            INodo<IEntry<K, V>> nodoPai = parent(nodo);

            remove(nodo);
            remove(nodoPai);
        }
    }

    public String printExpression(INodo<IEntry<K, V>> nodo) {
        String s = "";

        if (isInternal(nodo))
            s += "(";

        if (hasLeft(nodo))
            s += printExpression(left(nodo));

        if (nodo.element()!=null)
            s += nodo.element().getKey().toString();

        if (hasRight(nodo))
            s += printExpression(right(nodo));

        if (isInternal(nodo))
            s += ")";

        return s;
    }

    protected INodo<IEntry<K, V>> restructure(INodo<IEntry<K, V>> x) {
        INodoArvoreBinaria<IEntry<K, V>> a, b, c, t0, t1, t2, t3;

        INodo<IEntry<K, V>> y = parent(x);
        INodo<IEntry<K, V>> z = parent(y);
        INodoArvoreBinaria<IEntry<K, V>> xx = (INodoArvoreBinaria<IEntry<K, V>>) x;
        INodoArvoreBinaria<IEntry<K, V>> yy = (INodoArvoreBinaria<IEntry<K, V>>) y;
        INodoArvoreBinaria<IEntry<K, V>> zz = (INodoArvoreBinaria<IEntry<K, V>>) z;

        boolean xLeft = (x == left(y));
        boolean yLeft = (y == left(z));

        if (xLeft && yLeft) {
            a = xx;
            b = yy;
            c = zz;

            t0 = a.getLeft();
            t1 = a.getRight();
            t2 = b.getRight();
            t3 = c.getRight();
        } else if (!xLeft && yLeft) {
            a = yy;
            b = xx;
            c = zz;

            t0 = a.getLeft();
            t1 = b.getLeft();
            t2 = b.getRight();
            t3 = c.getRight();
        } else if (xLeft && !yLeft) {
            a = zz;
            b = xx;
            c = yy;

            t0 = a.getLeft();
            t1 = b.getLeft();
            t2 = b.getRight();
            t3 = c.getRight();
        } else {
            a = zz;
            b = yy;
            c = xx;

            t0 = a.getLeft();
            t1 = b.getLeft();
            t2 = c.getLeft();
            t3 = c.getRight();
        }

        if (isRoot(z)) {
            raiz = b;
            b.setParent(null);
        } else {
            INodoArvoreBinaria<IEntry<K, V>> zParent = (INodoArvoreBinaria<IEntry<K, V>>) parent(z);

            if (z == left(zParent)) {
                b.setParent(zParent);
                zParent.setLeft(b);
            } else {
                b.setParent(zParent);
                zParent.setRight(b);
            }
        }

        b.setLeft(a);
        a.setParent(b);
        a.setLeft(t0);
        t0.setParent(a);
        a.setRight(t1);
        t1.setParent(a);

        b.setRight(c);
        c.setParent(b);
        c.setLeft(t2);
        t2.setParent(c);
        c.setRight(t3);
        t3.setParent(c);

        ((ArvoreBinariaEntry<K, V>) a.element()).nodo = a;
        ((ArvoreBinariaEntry<K, V>) b.element()).nodo = b;
        ((ArvoreBinariaEntry<K, V>) c.element()).nodo = c;

        return b;
    }
}
