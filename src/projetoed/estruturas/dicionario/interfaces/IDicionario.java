package projetoed.estruturas.dicionario.interfaces;

import java.util.Map;

public interface IDicionario<K,V> {
    Map.Entry<K,V> get(K chave) throws IllegalArgumentException;
    Iterable<Map.Entry<K,V>> getAll(K chave) throws IllegalArgumentException;
    Map.Entry<K,V> put(K chave, V valor) throws IllegalArgumentException;
    Map.Entry<K,V> remove(Map.Entry<K,V> entry) throws IllegalArgumentException;
    Iterable<Map.Entry<K,V>> entrySet();
    int size();
    boolean isEmpty();
}
