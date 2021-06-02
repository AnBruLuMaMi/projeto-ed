package projetoed.estruturas.filaprioridade.interfaces;

import projetoed.estruturas.arvorebinaria.interfaces.IArvoreBinaria;
import projetoed.estruturas.listanodos.interfaces.INodo;

public interface IArvoreBinariaCompleta<T> extends IArvoreBinaria<T> {
    INodo<T> add(T elem);
    T remove();
}
