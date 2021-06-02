package projetoed.estruturas.mapa;

import projetoed.estruturas.filaprioridade.Entry;
import projetoed.estruturas.filaprioridade.exceptions.ChaveInvalidaException;
import projetoed.estruturas.filaprioridade.interfaces.IEntry;
import projetoed.estruturas.listanodos.ListaNodo;
import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.mapa.interfaces.IMapa;

public class HashTable<K,V> implements IMapa<K,V> {
    private IEntry<K, V> AVAILABLE = new HashEntry<K, V>(null, null);
    private int n = 0;
    private int prime, capacity;
    private IEntry<K, V>[] bucket;//
    private long scale, shift;

    public HashTable() {
        this(109345121, 1000);
    }

    public HashTable(int cap) {
        this(109345121, cap);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int prime, int capacity) {
        this.prime = prime;
        this.capacity = capacity;
        this.bucket = (IEntry<K, V>[]) new HashEntry[this.capacity];
        java.util.Random rand = new java.util.Random();
        this.scale = rand.nextInt(this.prime - 1) + 1;
        this.shift = rand.nextInt(this.prime);
    }

    private void validarChave(K chave) {
        if (chave == null)
            throw new ChaveInvalidaException("Chave inválida: null.");
    }

    private int hashValue(K chave) {
        return (int)((Math.abs(chave.hashCode() * scale + shift) % prime) % capacity);
    }

    private int findEntry(K chave) throws ChaveInvalidaException {
        int avail = -1;
        validarChave(chave);
        int i = hashValue(chave);
        int j = i;

        do {
            IEntry<K, V> entry = bucket[i];

            if (entry == null) {
                if (avail < 0)
                    avail = i;

                break;
            }

            if (chave.equals(entry.getKey()))
                return i;

            if (entry == AVAILABLE) {
                if (avail < 0)
                    avail = i;
            }

            i = (i + 1) % capacity;
        } while (i != j);

        return -(avail + 1);
    }

    private void rehash() {
        capacity = 2 * capacity;
        IEntry<K, V>[] old = bucket;

        bucket = (Entry<K, V>[]) new Entry[capacity];

        java.util.Random rand = new java.util.Random();

        scale = rand.nextInt(prime - 1) + 1;

        shift = rand.nextInt(prime);

        for (int i = 0; i < old.length; i++) {
            IEntry<K, V> entry = old[i];

            if ((entry != null) && (entry != AVAILABLE)) {
                int j = -1 - findEntry(entry.getKey());
                bucket[j] = entry;
            }
        }
    }

    @Override
    public V put(K chave, V valor) throws ChaveInvalidaException {
        int i = findEntry(chave);

        if (i >= 0)
            return ((HashEntry<K, V>) bucket[i]).setValue(valor);

        if (n >= capacity / 2) {
            rehash();
            i = findEntry(chave);
        }

        bucket[-i - 1] = new HashEntry<K, V>(chave, valor);
        n++;

        return null;
    }

    @Override
    public V get(K chave) throws ChaveInvalidaException {
        int i = findEntry(chave);

        if (i < 0)
            return null;

        return bucket[i].getValue();
    }

    @Override
    public V remove(K chave) throws ChaveInvalidaException {
        int i = findEntry(chave);

        if (i < 0)
            return null;

        V toReturn = bucket[i].getValue();

        bucket[i] = AVAILABLE;

        n--;

        return toReturn;
    }

    @Override
    public Iterable<K> keySet() {
        IListaNodo<K> chaves = new ListaNodo();

        for (int i = 0; i < capacity; i++)
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE))
                chaves.addLast(bucket[i].getKey());

        return chaves;
    }

    @Override
    public Iterable<V> values() {
        IListaNodo<V> valores = new ListaNodo();

        for (int i = 0; i < capacity; i++)
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE))
                valores.addLast(bucket[i].getValue());

        return valores;
    }

    @Override
    public Iterable<IEntry<K, V>> entrySet() {
        IListaNodo<IEntry<K, V>> entries = new ListaNodo();

        for (int i = 0; i < capacity; i++)
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE))
                entries.addLast(bucket[i]);

        return entries;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
