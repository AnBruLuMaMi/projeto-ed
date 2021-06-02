package projetoed.estruturas.dicionario;


import projetoed.estruturas.dicionario.interfaces.IDicionario;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Dicionario<K,V> implements IDicionario<K,V> {
    Map<K, LinkedList<Map.Entry<K,V>>> mapaChaves;
    int quantidadeElementos;

    public Dicionario() {
        this.mapaChaves = new HashMap<>();
        this.quantidadeElementos = 0;
    }

    @Override
    public Map.Entry<K, V> get(K chave) throws IllegalArgumentException {
        LinkedList<Map.Entry<K, V>> linkedList;

        if (chave == null) throw new IllegalArgumentException();

        if ((linkedList = mapaChaves.get(chave)) == null)
            return null;

        return linkedList.peekFirst();
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll(K chave) throws IllegalArgumentException {
        LinkedList<Map.Entry<K, V>> linkedList;

        if (chave == null)
            throw new IllegalArgumentException();

        if ((linkedList = mapaChaves.get(chave)) == null)
            return null;

        return linkedList;
    }

    @Override
    public Map.Entry<K, V> put(K chave, V valor) throws IllegalArgumentException {
        LinkedList<Map.Entry<K, V>> linkedList;

        if (chave == null)
            throw new IllegalArgumentException();

        if ((linkedList = mapaChaves.get(chave)) == null) {
            linkedList = new LinkedList<Map.Entry<K, V>>();
            mapaChaves.put(chave, linkedList);
        }

        Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<K, V>(chave, valor);

        linkedList.add(entry);
        quantidadeElementos++;

        return entry;
    }

    @Override
    public Map.Entry<K, V> remove(Map.Entry<K, V> entry) throws IllegalArgumentException {
        LinkedList<Map.Entry<K, V>> linkedList;

        if (entry == null)
            throw new IllegalArgumentException();

        K key = entry.getKey();
        linkedList = mapaChaves.get(key);

        if (linkedList == null)
            throw new IllegalArgumentException();

        if (linkedList.remove(entry)) {
            quantidadeElementos--;

            if (linkedList.isEmpty())
                mapaChaves.remove(key);

            return entry;
        } else
            throw new IllegalArgumentException();
    }

    @Override
    public Iterable<Map.Entry<K, V>> entrySet() {
        LinkedList<Map.Entry<K, V>> linkedList = new LinkedList<Map.Entry<K, V>>();

        for (LinkedList<Map.Entry<K, V>> sub : mapaChaves.values())
            linkedList.addAll(sub);

        return linkedList;
    }

    @Override
    public int size() {
        return quantidadeElementos;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
