package projetoed.menus;

import projetoed.estruturas.arvorebinaria.ArvoreBinaria;
import projetoed.estruturas.arvoregenerica.exceptions.ArvoreNaoVaziaException;
import projetoed.estruturas.arvoregenerica.exceptions.ArvoreVaziaException;
import projetoed.estruturas.listanodos.exceptions.LimiteVioladoException;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.INodo;
import projetoed.estruturas.mapa.HashTable;

import java.util.ArrayList;

public class ArvoreBinariaMenu extends MenuEstrutura {
    private ArvoreBinaria<String> arvoreBinaria;
    private HashTable<String, INodo<String>> variaveisRefNodo;
    private HashTable<String, ArvoreBinaria<String>> variaveisRefArvore;
    private int varNumNodo;
    private int varNumArvore;

    public ArvoreBinariaMenu() {
        super("Árvore Binária");
        arvoreBinaria = new ArvoreBinaria<>();
        variaveisRefNodo = new HashTable<>();
        variaveisRefArvore = new HashTable<>();
        varNumNodo = 0;
        varNumArvore = 0;

        paragrafos.add("A TAD Árvore binária é uma arvore ordenada que tem no máximo 2 filhos, sendo que o filho da esquerda procede o filho da direita na ordem dos " +
                "filhos. Uma árvore binaria pode ser tanto vazia quanto possuir um nodo raiz R que armazena um elemento, uma subárvore esquerda (a subárvore filho da " +
                "esquerda) e uma subárvore direita (a subárvore filho da direita).");

        opcoes.add(new OpcaoMenu("addRoot(e)", "Adiciona um elemento 'e' na raiz da árvore"));
        opcoes.add(new OpcaoMenu("insertLeft(v, e)", "Cria e retorna um nodo novo que se encontra como filho a esquerda de 'v', e que armazena o elemento 'e', " +
                "um erro ocorre se 'v' já tiver um filho da esquerda."));
        opcoes.add(new OpcaoMenu("insertRight(v, e)", "Cria e retorna um nodo novo que se encontra como filho a direita de 'v', e que armazena o elemento 'e', " +
                "um erro ocorre se 'v' já tiver um filho da direita."));
        opcoes.add(new OpcaoMenu("createBinaryTree(e)", "Método auxiliar que cria uma árvore binaria com elemento 'e' na raiz, para ser utilizado com o attach"));
        opcoes.add(new OpcaoMenu("attach(v,t1,t2)", "Conecta 'T1','T2', respectivamente, como as subárvores da esquerda e da\n" +
                "direita no nodo externo 'v'; um erro ocorre se 'v' não for um nodo externo"));
        opcoes.add(new OpcaoMenu("remove(v)", "Remove o nodo 'v', substituindo-o por seu filho, se houver algum, e " +
                "retorna o elemento armazenado em 'v'; um erro ocorre se v tiver dois filhos."));
        opcoes.add(new OpcaoMenu("left(v)", "Retorna o filho da esquerda de 'v'"));
        opcoes.add(new OpcaoMenu("right(v)", "Retorna o filho da direita de 'v'"));
        opcoes.add(new OpcaoMenu("hasLeft(v)", "Retorna se 'v' tem um filho na esquerda ou não"));
        opcoes.add(new OpcaoMenu("hasRight(v)", "Retorna se 'v' tem um filho na direita ou não"));
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
        comandos.add(new Comando("^insertLeft\\s*\\(\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> insertLeft(params)));
        comandos.add(new Comando("^insertLeft\\s*\\(\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> insertLeft(params)));
        comandos.add(new Comando("^insertRight\\s*\\(\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> insertRight(params)));
        comandos.add(new Comando("^insertRight\\s*\\(\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> insertRight(params)));
        comandos.add(new Comando("^createBinaryTree\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> createBinaryTree(params)));
        comandos.add(new Comando("^createBinaryTree\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> createBinaryTree(params)));
        ;
        comandos.add(new Comando("^attach\\s*\\(\\s*(.+)\\s*,\\s*(.+)\\s*,\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> attach(params)));
        comandos.add(new Comando("^remove\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> remove(params)));
        comandos.add(new Comando("^left\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> left(params)));
        comandos.add(new Comando("^right\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> right(params)));
        comandos.add(new Comando("^hasLeft\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> hasLeft(params)));
        comandos.add(new Comando("^hasRight\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> hasRight(params)));
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

    void armazenarRef(INodo<String> nodoRef) {
        String nomeVar = "nodo" + varNumNodo;
        varNumNodo++;

        variaveisRefNodo.put(nomeVar, nodoRef);
        escrever("A referência desse nodo foi armazenada na váriavel '" + nomeVar + "' para uso posterior");
    }

    void armazenarRefArvore(ArvoreBinaria<String> arvoreRef) {
        String nomeVar = "arvore" + varNumArvore;
        varNumArvore++;

        variaveisRefArvore.put(nomeVar, arvoreRef);
        escrever("A referência dessa árvore foi armazenada na váriavel '" + nomeVar + "' para uso posterior");
    }

    void addRoot(ArrayList<String> params) {
        try {
            String elemento = params.get(0);
            arvoreBinaria.addRoot(elemento);
            escrever("A raiz da árvore foi criada com o elemento '" + elemento + "'");
        } catch (ArvoreNaoVaziaException e) {
            escrever("Não é possível adicionar pois a árvore já possui uma raiz!");
        }
    }

    void insertLeft(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRefNodo.get(nomeVariavel);

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            String elemento = params.get(1);

            var nodoNovo = arvoreBinaria.insertLeft(nodoRef, elemento);
            escrever("O elemento '" + elemento + "' foi adicionado como filho da esquerda do nodo da váriavel '" + nomeVariavel + "'");
            armazenarRef(nodoNovo);
        } catch (NodoInvalidoException e) {
            escrever("Não foi possível inserir! Já existe um filho na esquerda do nodo!");
        }
    }

    void insertRight(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRefNodo.get(nomeVariavel);

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            String elemento = params.get(1);

            var nodoNovo = arvoreBinaria.insertRight(nodoRef, elemento);
            escrever("O elemento '" + elemento + "' foi adicionado como filho da direita do nodo da váriavel '" + nomeVariavel + "'");
            armazenarRef(nodoNovo);
        } catch (NodoInvalidoException e) {
            escrever("Não foi possível inserir! Já existe um filho na direita do nodo!");
        }
    }

    void createBinaryTree(ArrayList<String> params) {
        String elemento = params.get(0);

        ArvoreBinaria<String> arvoreBinaria = new ArvoreBinaria();
        arvoreBinaria.addRoot(elemento);
        armazenarRefArvore(arvoreBinaria);
    }

    void attach(ArrayList<String> params) {
        String nomeVariavelNodo = params.get(0);
        var nodoRef = variaveisRefNodo.get(nomeVariavelNodo);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavelNodo + "' não existe! Tente novamente!");
            return;
        }

        String nomeVariavelT1 = params.get(1);

        ArvoreBinaria T1 = variaveisRefArvore.get(nomeVariavelT1);

        if (T1 == null) {
            escrever("A váriavel '" + nomeVariavelT1 + "' não existe! Tente novamente!");
            return;
        }

        String nomeVariavelT2 = params.get(2);
        ArvoreBinaria T2 = variaveisRefArvore.get(nomeVariavelT2);

        if (T2 == null) {
            escrever("A váriavel '" + nomeVariavelT2 + "' não existe! Tente novamente!");
            return;
        }

        arvoreBinaria.attach(nodoRef, T1, T2);
        escrever("A árvore '" + nomeVariavelT1 + "' e a árvore '" + nomeVariavelT2 + "' foram adicionadas como filhas do nodo '" + nomeVariavelNodo + "'");
    }

    void remove(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRefNodo.get(nomeVariavel);

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            var elementoRemovido = arvoreBinaria.remove(nodoRef);
            escrever("O nodo com elemento '" + elementoRemovido + "' foi removido da árvore");
            variaveisRefNodo.remove(nomeVariavel);
        } catch (NodoInvalidoException e) {
            escrever("Não foi possível remover! O nodo possui dois filhos!");
        }
    }

    void root(ArrayList<String> params) {
        try {
            var nodo = arvoreBinaria.root();
            escrever("O elemento do nodo da raiz é '" + nodo.element() + "'");
            armazenarRef(nodo);
        } catch (ArvoreVaziaException e) {
            escrever("A árvore está vazia! Adicione algum elemento nela e tente novamente!");
        }
    }

    void parent(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRefNodo.get(params.get(0));

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            var nodo = arvoreBinaria.parent(nodoRef);
            escrever("O pai do nodo da váriavel '" + nomeVariavel + "' é o nodo com elemento '" + nodo.element() + "'");
            armazenarRef(nodo);
        } catch (LimiteVioladoException e) {
            escrever("O nodo não possui pai");
        }
    }

    void children(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRefNodo.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        var filhos = arvoreBinaria.children(nodoRef);

        if (filhos == null) {
            escrever("O nodo não possui filhos");
            return;
        }

        escrever("Os filhos do nodo da váriavel '" + nomeVariavel + "' são os nodos com elementos '" + filhos.toString() + "'");
    }

    void isInternal(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRefNodo.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        if (arvoreBinaria.isInternal(nodoRef))
            escrever("O nodo da váriavel '" + nomeVariavel + "' é interno!");
        else
            escrever("O nodo da váriavel '" + nomeVariavel + "' não é interno!");
    }

    void isExternal(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRefNodo.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        if (arvoreBinaria.isExternal(nodoRef))
            escrever("O nodo da váriavel '" + nomeVariavel + "' é externo!");
        else
            escrever("O nodo da váriavel '" + nomeVariavel + "' não é externo!");
    }

    void isRoot(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRefNodo.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        if (arvoreBinaria.isRoot(nodoRef))
            escrever("O nodo da váriavel '" + nomeVariavel + "' é a raiz da árvore!");
        else
            escrever("O nodo da váriavel '" + nomeVariavel + "' não é a raiz!");
    }

    void replace(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRefNodo.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        String elemento = params.get(1);

        var valorAntigo = arvoreBinaria.replace(nodoRef, elemento);
        escrever("O nodo da váriavel '" + nomeVariavel + "' teve seu elemento trocado de '" + valorAntigo + "' para '" + elemento + "'");
    }

    void left(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRefNodo.get(params.get(0));

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            var nodo = arvoreBinaria.left(nodoRef);
            escrever("O filho da esquerda do nodo da váriavel '" + nomeVariavel + "' é o nodo com elemento '" + nodo.element() + "'");
            armazenarRef(nodo);
        } catch (LimiteVioladoException e) {
            escrever("O nodo não possui filho na esquerda");
        }
    }

    void right(ArrayList<String> params) {
        try {
            String nomeVariavel = params.get(0);
            var nodoRef = variaveisRefNodo.get(params.get(0));

            if (nodoRef == null) {
                escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
                return;
            }

            var nodo = arvoreBinaria.right(nodoRef);
            escrever("O filho da direita do nodo da váriavel '" + nomeVariavel + "' é o nodo com elemento '" + nodo.element() + "'");
            armazenarRef(nodo);
        } catch (LimiteVioladoException e) {
            escrever("O nodo não possui filho na direita");
        }
    }

    void hasLeft(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRefNodo.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        if (arvoreBinaria.hasLeft(nodoRef))
            escrever("O nodo da váriavel '" + nomeVariavel + "' possui filho na esquerda!");
        else
            escrever("O nodo da váriavel '" + nomeVariavel + "' não possui filho na esquerda!");
    }

    void hasRight(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRefNodo.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        if (arvoreBinaria.hasRight(nodoRef))
            escrever("O nodo da váriavel '" + nomeVariavel + "' possui filho na direita!");
        else
            escrever("O nodo da váriavel '" + nomeVariavel + "' não possui filho na direita!");
    }

    void isEmpty(ArrayList<String> params) {
        if (arvoreBinaria.isEmpty())
            escrever("A árvore está vazia!");
        else
            escrever("A árvore não está vazia!");
    }

    void size(ArrayList<String> params) {
        escrever("A árvore possui " + arvoreBinaria.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(arvoreBinaria.toString());
    }
}
