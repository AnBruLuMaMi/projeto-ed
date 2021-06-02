package projetoed.menus;

import projetoed.estruturas.filaprioridade.FilaPrioridadeHeap;
import projetoed.estruturas.filaprioridade.exceptions.FilaPrioriadadeVaziaException;

import java.util.ArrayList;


public class FilaPrioridadeMenu extends MenuEstrutura {
    private FilaPrioridadeHeap<String, String> filaPrioridade;

    public FilaPrioridadeMenu() {
        super("Fila de Prioridade");
        filaPrioridade = new FilaPrioridadeHeap<>();

        paragrafos.add("A fila de prioridade armazena uma coleção de elementos priorizados que podem ser inseridos de forma arbritrária porém a remoção ocorre " +
                "somente em ordem de prioridade, ou seja elementos com prioridade mais alta podem ser removidos a qualquer momento. A fila de prioridade não tem um " +
                "conceito de posição, pois vê apenas os elementos de acordo com suas prioridades.");

        paragrafos.add("Para definir a prioridade de um elemento usamos chaves, uma chave pode ser definida por um usuário ou aplicação. Uma chave pode ser " +
                "definida como uma prioridade que um objeto não possui originalmente, uma chave não é necessariamente única e uma aplicação pode alterar a chave de um " +
                "elemento se for necessário.");

        opcoes.add(new OpcaoMenu("insert(k,x)", "Insere na fila a chave 'k' com valor 'x' e retorna a entrada armazenada; um erro ocorre se 'k' for inválido " +
                "('k' não pode ser comparado com outras chaves)"));
        opcoes.add(new OpcaoMenu("removeMin()", "Retorna e remove da fila o elemento com a menor chave (maior prioridade); um erro ocorre se a fila estiver vazia."));
        opcoes.add(new OpcaoMenu("min()", "Retorna sem remover o elemento com a menor chave na fila (maior prioridade); um erro ocorre se a fila estiver vazia."));
        opcoes.add(new OpcaoMenu("isEmpty()", "Retorna se a fila está vazia"));
        opcoes.add(new OpcaoMenu("size()", "Retorna o número de elementos na fila"));
        opcoes.add(new OpcaoMenu("toString()", "Exibe a fila"));

        comandos.add(new Comando("^insert\\s*\\(\\s*(-?\\d+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> insert(params)));
        comandos.add(new Comando("^insert\\s*\\(\\s*(-?\\d+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> insert(params)));
        comandos.add(new Comando("^insert\\s*\\(\\s*\"(.*)\"\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> insert(params)));
        comandos.add(new Comando("^insert\\s*\\(\\s*\"(.*)\"\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> insert(params)));
        comandos.add(new Comando("^removeMin\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> removeMin(params)));
        comandos.add(new Comando("^min\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> min(params)));
        comandos.add(new Comando("^isEmpty\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> isEmpty(params)));
        comandos.add(new Comando("^size\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> size(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }

    void insert(ArrayList<String> params) {
        String chave = params.get(0);
        String valor = params.get(1);

        var entrada = filaPrioridade.insert(chave, valor);
        escrever("A entrada '(" + entrada.getKey() + "," + entrada.getValue() + ")' foi inserida na fila");
    }

    void removeMin(ArrayList<String> params) {
        try {
            var min = filaPrioridade.removeMin();
            escrever("A entrada com a menor chave (maior prioridade) foi removido da fila '(" + min.getKey() + "," + min.getValue() + ")'");
        } catch (FilaPrioriadadeVaziaException e) {
            escrever("Não há entradas na fila! Adicione uma entrada na fila e tente novamente!");
        }
    }

    void min(ArrayList<String> params) {
        try {
            var min = filaPrioridade.min();
            escrever("A entrada com a menor chave (maior prioridade) é '(" + min.getKey() + "," + min.getValue() + ")'");
        } catch (FilaPrioriadadeVaziaException e) {
            escrever("Não há entradas na fila! Adicione uma entrada na fila e tente novamente!");
        }
    }

    void isEmpty(ArrayList<String> params) {
        if (filaPrioridade.isEmpty())
            escrever("A fila está vazia!");
        else
            escrever("A fila não está vazia!");
    }

    void size(ArrayList<String> params) {
        escrever("A fila possui " + filaPrioridade.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(filaPrioridade.toString());
    }
}
