package projetoed.menus;

import projetoed.estruturas.fila.FilaLSE;
import projetoed.estruturas.fila.exceptions.EmptyQueueException;

import java.util.ArrayList;

public class FilaMenu extends MenuEstrutura {
    private FilaLSE<String> fila;

    public FilaMenu() {
        super("Fila");
        fila = new FilaLSE<>();

        paragrafos.add("O TAD Fila é um conjunto de objetos que seguem a regra FIFO (Fisrt-In-Firts-Out) que traduzido para o português significa que quando o primeiro elemento, " +
                "que é nomeado como início da fila é inserido neste conjunto, o mesmo também passa a ser o primeiro elemento removido do mesmo. Ao inserir mais elementos, o " +
                "último elemento inserido é chamado de fim da fila.");

        paragrafos.add("Para inserir um elemento a lista basta usar o método enqueue(e); para remove-lo use o método dequeue(); e por fim, para visualizar a quantidade de " +
                "elementos basta usar o método size().");

        opcoes.add(new OpcaoMenu("enqueue(e)", "Insere o elemento 'e' no fim da fila"));
        opcoes.add(new OpcaoMenu("dequeue()", "Remove e retorna o elemento na frente da fila"));
        opcoes.add(new OpcaoMenu("front()", "Retorna, mas não remove o elemento na frente da fila"));
        opcoes.add(new OpcaoMenu("isEmpty()", "Retorna se a fila está vazia ou não"));
        opcoes.add(new OpcaoMenu("size()", "Retorna o número de elementos na fila"));
        opcoes.add(new OpcaoMenu("toString()", "Exibe a fila"));

        comandos.add(new Comando("^enqueue\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> enqueue(params)));
        comandos.add(new Comando("^enqueue\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> enqueue(params)));
        comandos.add(new Comando("^dequeue\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> dequeue(params)));
        comandos.add(new Comando("^front\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> front(params)));
        comandos.add(new Comando("^isEmpty\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> isEmpty(params)));
        comandos.add(new Comando("^size\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> size(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }

    void enqueue(ArrayList<String> params) {
        try {
            String elemento = params.get(0);

            fila.enqueue(elemento);
            escrever("O elemento '" + elemento + "' foi adicionado no final da fila");
        } catch (NumberFormatException e) {
            escrever("O elemento deve ser um número inteiro! Tente novamente!");
        }
    }

    void dequeue(ArrayList<String> params) {
        try {
            String elementoRemovido = fila.dequeue();
            escrever("O elemento '" + elementoRemovido + "' foi removido da frente da fila");
        } catch (EmptyQueueException e) {
            escrever("Não há elementos na fila! Adicione um elemento na fila e tente novamente!");
        }
    }

    void front(ArrayList<String> params) {
        try {
            escrever("O elemento na frente da fila é '" + fila.front() + "'");
        } catch (EmptyQueueException e) {
            escrever("Não há elementos na fila! Adicione um elemento na fila e tente novamente!");
        }
    }

    void isEmpty(ArrayList<String> params) {
        if (fila.isEmpty())
            escrever("A fila está vazia!");
        else
            escrever("A fila não está vazia!");
    }

    void size(ArrayList<String> params) {
        escrever("A fila possui " + fila.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(fila.toString());
    }
}
