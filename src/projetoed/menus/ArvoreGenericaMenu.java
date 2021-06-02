package projetoed.menus;

import projetoed.estruturas.arvoregenerica.Arvore;
import projetoed.estruturas.arvoregenerica.exceptions.ArvoreNaoVaziaException;
import projetoed.estruturas.arvoregenerica.exceptions.ArvoreVaziaException;
import projetoed.estruturas.arvoregenerica.interfaces.INodoArvore;
import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.interfaces.INodo;
import projetoed.estruturas.mapa.HashTable;

import java.util.ArrayList;

public class ArvoreGenericaMenu extends MenuEstrutura {
    private Arvore<String> arvoreGenerica;
    private HashTable<String, INodo<String>> variaveisRef;
    private int varNum;

    public ArvoreGenericaMenu() {
        super("Árvore Genérica");
        arvoreGenerica = new Arvore<>();
        variaveisRef = new HashTable<>();
        varNum = 0;

        paragrafos.add("A TAD Arvores são as estruturas de dados não lineares mais importantes da computação, tendo uma rapidez nos algoritmos mais interessante do " +
                "que nas estruturas de dados lineares, como as listas.");

        paragrafos.add("Para descrever as estruturas de dados das árvores, usam-se os termos “pai”, “filho”, “ancestral” e “descendente”, ou seja, armazenando " +
                "elementos de uma forma hierárquica, onde todos tem um “pai”, com exceção do topo que é nomeado de raiz, e podendo ter 0 ou mais “filhos”.");

        paragrafos.add("Para definir uma árvore, uma árvore T é um conjunto de nodos que armazenam elementos pai e filhos, onde tem um nodo chamado raiz, que não " +
                "possui pai e cada novo V da árvore T tem um único pai podendo ter 0 ou mais filhos. E se a árvore é vazia, ela não possui nodos. Quando não há " +
                "filhos, o nodo é chamado de externo, ou folhas, e quando há 1 ou mais filhos, o nodo é chamado de interno. E o TAD Árvore é armazenado em posições " +
                "como a de listas, definindo as posições de seus vizinhos, onde as posições são os nodos.");

        opcoes.add(new OpcaoMenu("addRoot(e)", "Adiciona um elemento 'e' na raiz da árvore"));
        opcoes.add(new OpcaoMenu("addChild(v, e)", "Adiciona um elemento 'e' como filho do nodo 'v'"));
        opcoes.add(new OpcaoMenu("root()", "Retorna a raiz da árvore, indicando erro caso não tenha raiz"));
        opcoes.add(new OpcaoMenu("parent(V)", "Retorna o nodo pai de 'V'"));
        opcoes.add(new OpcaoMenu("children(v)", "Retorna os filhos do nodo 'v', e caso 'v' seja um nodo externo, esse último método retornará vazio."));
        opcoes.add(new OpcaoMenu("isInternal(v)", "Retorna se o nodo 'V' é interno ou não"));
        opcoes.add(new OpcaoMenu("isExternal(v)", "Retorna se o nodo 'V' é externo ou não"));
        opcoes.add(new OpcaoMenu("isRoot(v)", "Retorna se o nodo 'V' é a raiz ou não"));
        opcoes.add(new OpcaoMenu("replace(v, e)", "Retorna o elemento armazenado em 'V' e substitui por 'E'"));
        opcoes.add(new OpcaoMenu("isEmpty()", "Retorna se a árvore está vazia ou não"));
        opcoes.add(new OpcaoMenu("size()", "Retorna o número de elementos armazenados na árvore"));
        opcoes.add(new OpcaoMenu("toString()", "Exibe a árvore"));

        comandos.add(new Comando("^addRoot\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> addRoot(params)));
        comandos.add(new Comando("^addRoot\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> addRoot(params)));
        comandos.add(new Comando("^addChild\\s*\\(\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> addChild(params)));
        comandos.add(new Comando("^addChild\\s*\\(\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> addChild(params)));
        comandos.add(new Comando("^root\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> root(params)));
        comandos.add(new Comando("^parent\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> parent(params)));
        comandos.add(new Comando("^children\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> children(params)));
        comandos.add(new Comando("^isInternal\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> isInternal(params)));
        comandos.add(new Comando("^isExternal\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> isExternal(params)));
        comandos.add(new Comando("^isRoot\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> isRoot(params)));
        comandos.add(new Comando("^replace\\s*\\(\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> replace(params)));
        comandos.add(new Comando("^replace\\s*\\(\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> replace(params)));
        comandos.add(new Comando("^isEmpty\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> isEmpty(params)));
        comandos.add(new Comando("^size\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> size(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }

    void armazenarRef(INodoArvore<String> nodoRef) {
        String nomeVar = "elemento" + varNum;
        varNum++;

        variaveisRef.put(nomeVar, nodoRef);
        escrever("A referência desse nodo foi armazenada na váriavel '" + nomeVar + "' para uso posterior");
    }

    void addRoot(ArrayList<String> params) {
        try {
            String elemento = params.get(0);
            arvoreGenerica.addRoot(elemento);
            escrever("A raiz da árvore foi criada com o elemento '" + elemento + "'");
        } catch (ArvoreNaoVaziaException e) {
            escrever("Não é possível adicionar pois a árvore já possui uma raiz!");
        }
    }

    void addChild(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        String elemento = params.get(1);

        var nodoCriado = arvoreGenerica.addChild(nodoRef, elemento);
        escrever("O elemento '" + elemento + "' foi adicionado como filho do nodo da váriavel '" + nomeVariavel + "'");
        armazenarRef(nodoCriado);
    }

    void root(ArrayList<String> params) {
        try {
            var nodo = arvoreGenerica.root();
            escrever("O elemento do nodo da raiz é '" + nodo.element() + "'");
            armazenarRef(nodo);
        } catch (ArvoreVaziaException e) {
            escrever("A árvore está vazia! Adicione algum elemento nela e tente novamente!");
        }
    }

    void parent(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRef.get(params.get(0));

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            var nodo = arvoreGenerica.parent(nodoRef);
            escrever("O pai do nodo da váriavel '" + nomeVariavel + "' é o nodo com elemento '" + nodo.element() + "'");
            armazenarRef(nodo);
        } catch (LimiteVioladoException e) {
            escrever("O nodo não possui pai");
        }
    }

    void children(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        var filhos = arvoreGenerica.children(nodoRef);

        if (filhos == null) {
            escrever("O nodo não possui filhos");
            return;
        }

        escrever("Os filhos do nodo da váriavel '" + nomeVariavel + "' são os nodos com elementos '" + filhos.toString() + "'");
    }

    void isInternal(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        if (arvoreGenerica.isInternal(nodoRef))
            escrever("O nodo da váriavel '" + nomeVariavel + "' é interno!");
        else
            escrever("O nodo da váriavel '" + nomeVariavel + "' não é interno!");
    }

    void isExternal(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        if (arvoreGenerica.isExternal(nodoRef))
            escrever("O nodo da váriavel '" + nomeVariavel + "' é externo!");
        else
            escrever("O nodo da váriavel '" + nomeVariavel + "' não é externo!");
    }

    void isRoot(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        if (arvoreGenerica.isRoot(nodoRef))
            escrever("O nodo da váriavel '" + nomeVariavel + "' é a raiz da árvore!");
        else
            escrever("O nodo da váriavel '" + nomeVariavel + "' não é a raiz!");
    }

    void replace(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        String elemento = params.get(1);

        var valorAntigo = arvoreGenerica.replace(nodoRef, elemento);
        escrever("O nodo da váriavel '" + nomeVariavel + "' teve seu elemento trocado de '" + valorAntigo +"' para '" + elemento + "'");
    }

    void isEmpty(ArrayList<String> params) {
        if (arvoreGenerica.isEmpty())
            escrever("A árvore está vazia!");
        else
            escrever("A árvore não está vazia!");
    }

    void size(ArrayList<String> params) {
        escrever("A árvore possui " + arvoreGenerica.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(arvoreGenerica.toString());
    }
}
