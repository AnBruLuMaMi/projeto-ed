package projetoed.menus;

import projetoed.estruturas.pilha.PilhaLSE;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class PilhaMenu extends MenuEstrutura {
    private PilhaLSE<String> pilha;

    public PilhaMenu() {
        super("Pilha");
        pilha = new PilhaLSE<>();

        paragrafos.add("A TAD pilha é uma coleção de objetos que são inseridos e retirados de acordo com a ordem é inserida na pilha, onde o primeiro inserido será o " +
                "último retirado, e logicamente, o ultimo inserido será o primeiro retirado, chamado LIFO (last in, first out).");

        opcoes.add(new OpcaoMenu("push(elemento)", "Insere um elemento no topo da pilha"));
        opcoes.add(new OpcaoMenu("pop()", "Remove o elemento que se encontra no topo da pilha, ocorrendo erro se a pilha estiver vazia"));
        opcoes.add(new OpcaoMenu("top()", "Retorna o elemento no topo da pilha"));
        opcoes.add(new OpcaoMenu("isEmpty()", "Retorna se a pilha está vazia ou não"));
        opcoes.add(new OpcaoMenu("size()", "Retorna o número de elementos da pilha"));
        opcoes.add(new OpcaoMenu("toString()", "Exibe a pilha"));

        comandos.add(new Comando("^push\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> push(params)));
        comandos.add(new Comando("^push\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> push(params)));
        comandos.add(new Comando("^pop\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> pop(params)));
        comandos.add(new Comando("^top\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> top(params)));
        comandos.add(new Comando("^isEmpty\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> isEmpty(params)));
        comandos.add(new Comando("^size\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> size(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }

    void push(ArrayList<String> params) {
        String elemento = params.get(0);

        pilha.push(elemento);
        escrever("O elemento '" + elemento + "' foi adicionado no topo da pilha");
    }

    void pop(ArrayList<String> params) {
        try {
            String elementoRemovido = pilha.pop();
            escrever("O elemento '" + elementoRemovido + "' foi removido do topo da pilha");
        } catch (EmptyStackException e) {
            escrever("Não há elementos na pilha! Adicione um elemento na pilha e tente novamente!");
        }
    }

    void top(ArrayList<String> params) {
        try {
            escrever("O elemento no topo da pilha é '" + pilha.top() + "'");
        } catch (EmptyStackException e) {
            escrever("Não há elementos na pilha! Adicione um elemento na pilha e tente novamente!");
        }
    }

    void isEmpty(ArrayList<String> params) {
        if (pilha.isEmpty())
            escrever("A pilha está vazia!");
        else
            escrever("A pilha não está vazia!");
    }

    void size(ArrayList<String> params) {
        escrever("A pilha possui " + pilha.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(pilha.toString());
    }
}
