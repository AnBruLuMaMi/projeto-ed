package projetoed.menus;

import projetoed.estruturas.listanodos.ListaNodo;
import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.exceptions.ListaVaziaException;
import projetoed.estruturas.listanodos.interfaces.INodo;
import projetoed.estruturas.mapa.HashTable;

import java.util.ArrayList;

public class ListaNodoMenu extends MenuEstrutura {
    private ListaNodo<String> listaNodo;
    private HashTable<String, INodo<String>> variaveisRef;
    private int varNum;

    public ListaNodoMenu() {
        super("Lista de Nodos");
        listaNodo = new ListaNodo<>();
        variaveisRef = new HashTable<>();
        varNum = 0;

        paragrafos.add("O TAD Lista de Nodos, é uma lista com uma sequência de nodos, que por sua vez, são estruturas que armazenam informações a serem gerenciadas por uma " +
                "lista, e permitem sua atualização, diferente do índice, que só retorna a posição do elemento desejado. Este conceito, traz à existência o TAD Posição, que " +
                "possui um único método “element()”, com a função de retornar o elemento armazenado na posição do nodo desejado");

        opcoes.add(new OpcaoMenu("addFirst(e)", "Insere o elemento 'e' na primeira posição da lista"));
        opcoes.add(new OpcaoMenu("addLast(e)", "Insere o elemento 'e' na última posição da lista"));
        opcoes.add(new OpcaoMenu("addAfter(p, e)", "Insere o elemento 'e' antes da posição 'p'"));
        opcoes.add(new OpcaoMenu("addBefore(p, e)", "Insere o elemento 'e' antes da posição 'p'"));
        opcoes.add(new OpcaoMenu("remove(p)", "Remove e retorna o elemento na posição 'p', tornando essa posição inválida"));
        opcoes.add(new OpcaoMenu("set(p, e)", "Substitui o elemento armazenado na posição 'p' por 'e', e retorna o elemento que foi substituído"));
        opcoes.add(new OpcaoMenu("first()", "Retorna a posição do primeiro elemento da lista. Se a lista estiver vazia, ocorrerá um erro"));
        opcoes.add(new OpcaoMenu("last()", "Retorna a posição do último elemento da lista. Se a lista estiver vazia, ocorrerá um erro"));
        opcoes.add(new OpcaoMenu("next(p)", "Retorna a posição do elemento que vem depois do que está na posição 'p'. Se 'p' for a última posição, ocorrerá um erro"));
        opcoes.add(new OpcaoMenu("prev(p)", "Retorna a posição do elemento que vem antes do que está na posição 'p'. Se 'p' for a primeira posição, ocorrerá um erro"));
        opcoes.add(new OpcaoMenu("isEmpty()", "Retorna se a lista está vazia ou não"));
        opcoes.add(new OpcaoMenu("size()", "Retorna o tamanho da lista"));
        opcoes.add(new OpcaoMenu("toString()", "Exibe a lista"));

        comandos.add(new Comando("^addFirst\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> addFirst(params)));
        comandos.add(new Comando("^addFirst\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> addFirst(params)));
        comandos.add(new Comando("^addLast\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> addLast(params)));
        comandos.add(new Comando("^addLast\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> addLast(params)));
        comandos.add(new Comando("^addAfter\\s*\\(\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> addAfter(params)));
        comandos.add(new Comando("^addAfter\\s*\\(\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> addAfter(params)));
        comandos.add(new Comando("^addBefore\\s*\\(\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> addBefore(params)));
        comandos.add(new Comando("^addBefore\\s*\\(\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> addBefore(params)));
        comandos.add(new Comando("^remove\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> remove(params)));
        comandos.add(new Comando("^set\\s*\\(\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> set(params)));
        comandos.add(new Comando("^set\\s*\\(\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> set(params)));
        comandos.add(new Comando("^first\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> first(params)));
        comandos.add(new Comando("^last\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> last(params)));
        comandos.add(new Comando("^next\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> next(params)));
        comandos.add(new Comando("^prev\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> prev(params)));
        comandos.add(new Comando("^isEmpty\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> isEmpty(params)));
        comandos.add(new Comando("^size\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> size(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }

    void armazenarRef(INodo<String> nodoRef) {
          String nomeVar = "elemento" + varNum;
          varNum++;

          variaveisRef.put(nomeVar, nodoRef);
          escrever("A referência desse nodo foi armazenada na váriavel '" + nomeVar + "' para uso posterior");
    }

    void addFirst(ArrayList<String> params) {
        String elemento = params.get(0);
        listaNodo.addFirst(elemento);
        escrever("O elemento '" + elemento + "' foi adicionado no começo da lista");
    }

    void addLast(ArrayList<String> params) {
        String elemento = params.get(0);
        listaNodo.addFirst(elemento);
        escrever("O elemento '" + elemento + "' foi adicionado no final da lista");
    }

    void addAfter(ArrayList<String> params) {
          String nomeVariavel = params.get(0);
          var nodoRef = variaveisRef.get(nomeVariavel);

          if (nodoRef == null) {
              escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
              return;
          }

          String elemento = params.get(1);

          listaNodo.addAfter(nodoRef, elemento);
          escrever("O elemento '" + elemento + "' foi adicionado após o nodo da váriavel '" + nomeVariavel + "'");
    }

    void addBefore(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        String elemento = params.get(1);

        listaNodo.addBefore(nodoRef, elemento);
        escrever("O elemento '" + elemento + "' foi adicionado antes do nodo da váriavel '" + nomeVariavel + "'");
    }

    void remove(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        var elementoRemovido = listaNodo.remove(nodoRef);
        escrever("O nodo com valor '" + elementoRemovido + "' foi removido da lista");

        variaveisRef.remove(nomeVariavel);
    }

    void set(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        String elemento = params.get(1);

        Object elementoAntigo = listaNodo.set(nodoRef, elemento);

        escrever("O nodo da váriavel '" + nomeVariavel + "' teve seu elemento alterado de '" + elementoAntigo + "' para '" + elemento + "'");
    }

    void first(ArrayList<String> params) {
        try {
            var nodo = listaNodo.first();
            escrever("O elemento do primeiro nodo é '" + nodo.element()  + "'");
            armazenarRef(nodo);
        } catch (ListaVaziaException e) {
            escrever("A lista está vazia! Adicione algum elemento nela e tente novamente!");
        }
    }

    void last(ArrayList<String> params) {
        try {
            var nodo = listaNodo.last();
            escrever("O elemento do último nodo é '" + nodo.element()  + "'");
            armazenarRef(nodo);
        } catch (ListaVaziaException e) {
            escrever("A lista está vazia! Adicione algum elemento nela e tente novamente!");
        }
    }

    void next(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRef.get(nomeVariavel);

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            var nodo = listaNodo.next(nodoRef);
            escrever("Após o nodo da váriavel '" + nomeVariavel +"' encontra-se o nodo com elemento '" + nodo.element() + "'");
            armazenarRef(nodo);
        } catch (LimiteVioladoException e) {
            escrever("Não há nodos após");
        }
    }

    void prev(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRef.get(nomeVariavel);

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            var nodo = listaNodo.prev(nodoRef);
            escrever("Antes do nodo da váriavel '" + nomeVariavel +"' encontra-se o nodo com elemento '" + nodo.element() + "'");
            armazenarRef(nodo);
        } catch (LimiteVioladoException e) {
            escrever("Não há nodos antes");
        }
    }

    void isEmpty(ArrayList<String> params) {
        if (listaNodo.isEmpty())
            escrever("A lista de nodos está vazia!");
        else
            escrever("A lista de nodos não está vazia!");
    }

    void size(ArrayList<String> params) {
        escrever("A lista de nodos possui " + listaNodo.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(listaNodo.toString());
    }
}
