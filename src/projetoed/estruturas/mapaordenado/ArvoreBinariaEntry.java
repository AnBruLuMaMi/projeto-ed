package projetoed.estruturas.mapaordenado;

import projetoed.estruturas.filaprioridade.interfaces.IEntry;
import projetoed.estruturas.listanodos.interfaces.INodo;

public class ArvoreBinariaEntry<K, V> implements IEntry<K, V> {
    protected K chave;
    protected V valor;
    protected INodo<IEntry<K, V>> nodo;

    ArvoreBinariaEntry(K chave, V valor, INodo<IEntry<K, V>> nodo) {
        this.chave = chave;
        this.valor = valor;
        this.nodo = nodo;
    }

    public K getKey() { return chave; }
    public V getValue() { return valor; }
    public INodo<IEntry<K, V>> position() { return nodo; }

    @Override
    public String toString() {
        return "{" + chave + "," + valor + "}";
    };
}

