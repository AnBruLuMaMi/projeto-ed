package projetoed.estruturas.filaprioridade.interfaces;

import projetoed.estruturas.filaprioridade.exceptions.FilaPrioriadadeVaziaException;
import projetoed.estruturas.filaprioridade.exceptions.ChaveInvalidaException;

public interface IFilaPrioridade<K,V> {
    IEntry<K,V> min() throws FilaPrioriadadeVaziaException;
    IEntry<K,V> insert(K chave, V valor) throws ChaveInvalidaException;
    IEntry<K,V> removeMin( ) throws FilaPrioriadadeVaziaException;
    int size();
    boolean isEmpty( );
}
