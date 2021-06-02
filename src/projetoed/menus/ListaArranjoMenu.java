package projetoed.menus;

import projetoed.estruturas.listaarranjo.ListaArranjo;

import java.util.ArrayList;

public class ListaArranjoMenu extends MenuEstrutura {
    private ListaArranjo<String> listaArranjo;

    public ListaArranjoMenu() {
        super("Lista Arranjo");
        listaArranjo = new ListaArranjo<>();

        paragrafos.add("TAD Lista Arranjo, é um agrupamento de elementos que os armazenam de maneira linear. Estes elementos podem ser referidos por elementos S de N. É possível " +
                "acessar o elemento e de S usando um inteiro no intervalo, pois o índice (lugar onde o elemento está localizado) é igual ao número de elementos de S que " +
                "antecede e.");

        paragrafos.add("É possível inserir um elemento ao TAD Lista Arranjo usando o método de add(i,e); também é possível remove-lo usando o método remove(i); e " +
                "para visualizar este elemento dentro da TAD basta usar o método get(i).");

        opcoes.add(new OpcaoMenu("add(i, e)", "Insere um elemento novo 'e' no índice 'i' do arranjo"));
        opcoes.add(new OpcaoMenu("remove(i)", "Remove do arranjo o elemento de índice 'i'"));
        opcoes.add(new OpcaoMenu("get(i)", "Retorna o elemento do arranjo com índice 'i'"));
        opcoes.add(new OpcaoMenu("set(i, e)", "Substitui o elemento no índice 'i' do arranjo por 'e' e retorna o elemento que foi substituido"));
        opcoes.add(new OpcaoMenu("isEmpty()", "Retorna se o arranjo está vazio ou não"));
        opcoes.add(new OpcaoMenu("size()", "Retorna o número de elementos do arranjo"));
        opcoes.add(new OpcaoMenu("toString()", "Exibe o arranjo"));

        comandos.add(new Comando("^add\\s*\\(\\s*(\\d+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> add(params)));
        comandos.add(new Comando("^add\\s*\\(\\s*(\\d+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> add(params)));
        comandos.add(new Comando("^remove\\s*\\(\\s*(\\d+)\\s*\\)\\s*;*\\s*$", (params) -> remove(params)));
        comandos.add(new Comando("^get\\s*\\(\\s*(\\d+)\\s*\\)\\s*;*\\s*$", (params) -> get(params)));
        comandos.add(new Comando("^set\\s*\\(\\s*(\\d+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> set(params)));
        comandos.add(new Comando("^set\\s*\\(\\s*(\\d+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> set(params)));
        comandos.add(new Comando("^isEmpty\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> isEmpty(params)));
        comandos.add(new Comando("^size\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> size(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }

    void add(ArrayList<String> params) {
        try {
            int pos = Integer.parseInt(params.get(0));
            String elemento = params.get(1);

            listaArranjo.add(pos, elemento);
            escrever("O elemento '" + elemento + "' foi adicionado no índice '" + pos + "'");
        } catch (IndexOutOfBoundsException e) {
            escrever("O índice digitado está fora do limite do arranjo! Tente novamente com um índice válido!");
        } catch (NumberFormatException e) {
            escrever("O índice deve ser um número inteiro! Tente novamente!");
        }
    }

    void remove(ArrayList<String> params) {
        try {
            int pos = Integer.parseInt(params.get(0));

            escrever("O elemento '" + listaArranjo.remove(pos) + "' foi removido do índice '" + pos + "'");
        } catch (IndexOutOfBoundsException e) {
            escrever("O índice digitado está fora do limite do arranjo! Tente novamente com um índice válido!");
        } catch (NumberFormatException e) {
            escrever("O índice deve ser um número inteiro! Tente novamente!");
        }
    }

    void get(ArrayList<String> params) {
        try {
            int pos = Integer.parseInt(params.get(0));

            escrever("O elemento armazenado no índice '" + pos + "' é '" + listaArranjo.get(pos) + "'");
        } catch (IndexOutOfBoundsException e) {
            escrever("O índice digitado está fora do limite do arranjo! Tente novamente com um índice válido!");
        } catch (NumberFormatException e) {
            escrever("O índice deve ser um número inteiro! Tente novamente!");
        }
    }

    void set(ArrayList<String> params) {
        try {
            int pos = Integer.parseInt(params.get(0));
            String elemento = params.get(1);

            escrever("O elemento '" + listaArranjo.set(pos, elemento) + "' no índice '" + pos + "' foi alterado para o elemento '" + elemento + "'");
        } catch (IndexOutOfBoundsException e) {
            escrever("O índice digitado está fora do limite do arranjo! Tente novamente com um índice válido!");
        } catch (NumberFormatException e) {
            escrever("O índice deve ser um número inteiro! Tente novamente!");
        }
    }

    void isEmpty(ArrayList<String> params) {
        if (listaArranjo.isEmpty())
            escrever("A lista de arranjo está vazia!");
        else
            escrever("A lista de arranjo não está vazia!");
    }

    void size(ArrayList<String> params) {
        escrever("A lista de arranjo possui " + listaArranjo.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(listaArranjo.toString());
    }
}
