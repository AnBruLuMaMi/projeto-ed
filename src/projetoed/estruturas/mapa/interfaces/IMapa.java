package projetoed.estruturas.mapa.interfaces;

import projetoed.estruturas.filaprioridade.exceptions.ChaveInvalidaException;
import projetoed.estruturas.filaprioridade.interfaces.IEntry;

public interface IMapa<K,V> {
    V put(K chave, V valor) throws ChaveInvalidaException;
    V get(K chave) throws ChaveInvalidaException;
    V remove(K chave) throws ChaveInvalidaException;
    Iterable<K> keySet();
    Iterable<V> values();
    Iterable<IEntry<K,V>> entrySet();
    int size();
    boolean isEmpty();
}
