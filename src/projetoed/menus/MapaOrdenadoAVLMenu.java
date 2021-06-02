package projetoed.menus;

import projetoed.estruturas.mapaordenadoavl.MapaOrdenadoAVL;

import java.util.ArrayList;

public class MapaOrdenadoAVLMenu extends MenuEstrutura {
    private MapaOrdenadoAVL<String, String> mapaOrdenadoAVL;

    public MapaOrdenadoAVLMenu() {
        super("Mapa Ordenado - AVL");
        mapaOrdenadoAVL = new MapaOrdenadoAVL<>();

        paragrafos.add("A árvore AVL surgiu a partir de uma correção feita na definição de árvore binária de busca, a correção consiste na adição da Propriedade de Balanceamento, " +
                "que diz que para cada nodo interno de uma ABB, as alturas dos filhos deste nodo não podem variar mais que 1. Sendo assim, toda árvore binária de busca que siga " +
                "essa regra será considerada uma árvore AVL");

        opcoes.add(new OpcaoMenu("put(k,v)", "Se não há nenhum elemento com a chave igual a 'k' no mapa o conjunto chave-valor (k,v) é " +
                "adicionado ao mesmo e retorna null; senão, substitui o valor de já no mapa com a chave 'k' pelo valor de 'v' e retorna o valor antigo"));
        opcoes.add(new OpcaoMenu("remove(k)", "Remove o elemento do mapa com a chave igual a 'k', e retorna seu valor; se o mapa não possuir " +
                "nenhum elemento com chave 'k' retorna null"));
        opcoes.add(new OpcaoMenu("get(k)", "Se o mapa contém um valor com a chave 'k', retorna tal valor caso contrário retorna null"));
        opcoes.add(new OpcaoMenu("keySet()", "Retorna uma coleção iterável com todas as chaves dentro do mapa"));
        opcoes.add(new OpcaoMenu("values()", "Retorna uma coleção iterável com todos os valores associados as chaves no mapa."));
        opcoes.add(new OpcaoMenu("entrySet()", "Retorna uma coleção iterável com todos os conjuntos chave-valor do mapa"));
        opcoes.add(new OpcaoMenu("isEmpty()", "Retorna se o mapa está vazio ou não"));
        opcoes.add(new OpcaoMenu("size()", "Retorna o número de elementos no mapa"));
        opcoes.add(new OpcaoMenu("toString()", "Exibe o mapa"));

        comandos.add(new Comando("^put\\s*\\(\\s*(-?\\d+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> put(params)));
        comandos.add(new Comando("^put\\s*\\(\\s*(-?\\d+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> put(params)));
        comandos.add(new Comando("^put\\s*\\(\\s*\"(.*)\"\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> put(params)));
        comandos.add(new Comando("^put\\s*\\(\\s*\"(.*)\"\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> put(params)));
        comandos.add(new Comando("^remove\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> remove(params)));
        comandos.add(new Comando("^remove\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> remove(params)));
        comandos.add(new Comando("^get\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> get(params)));
        comandos.add(new Comando("^get\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> get(params)));
        comandos.add(new Comando("^keySet\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> keySet(params)));
        comandos.add(new Comando("^values\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> values(params)));
        comandos.add(new Comando("^entrySet\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> entrySet(params)));
        comandos.add(new Comando("^isEmpty\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> isEmpty(params)));
        comandos.add(new Comando("^size\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> size(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }


    void put(ArrayList<String> params) {
        String chave = params.get(0);
        String valor = params.get(1);

        var valorAntigo = mapaOrdenadoAVL.put(chave, valor);

        if (valorAntigo != null)
            escrever("O valor '" + valorAntigo + "' da chave '" + chave + "' foi substituido por '" + valor + "'");
        else
            escrever("A entrada (" + chave + "," + valor + ") foi armazenada");
    }

    void remove(ArrayList<String> params) {
        String chave = params.get(0);

        var valorRemovido = mapaOrdenadoAVL.remove(chave);

        if (valorRemovido != null)
            escrever("O valor '" + valorRemovido + "' foi removido da chave '" + chave + "'");
        else
            escrever("Não existe entrada com a chave '" + chave + "'");
    }

    void get(ArrayList<String> params) {
        String chave = params.get(0);

        var valorObtido = mapaOrdenadoAVL.get(chave);

        if (valorObtido != null)
            escrever("O valor '" + valorObtido + "' está armazenado na entrada com a chave '" + chave + "'");
        else
            escrever("Não existe entrada com a chave '" + chave + "'");
    }

    void keySet(ArrayList<String> params) {
        escrever("As chaves armazenadas são: " + mapaOrdenadoAVL.keySet().toString());
    }

    void values(ArrayList<String> params) {
        escrever("Os valores armazenados são: " + mapaOrdenadoAVL.values().toString());
    }

    void entrySet(ArrayList<String> params) {
        escrever("As entradas armazenadas são: " + mapaOrdenadoAVL.entrySet().toString());
    }

    void isEmpty(ArrayList<String> params) {
        if (mapaOrdenadoAVL.isEmpty())
            escrever("O mapa está vazio!");
        else
            escrever("O mapa não está vazio!");
    }

    void size(ArrayList<String> params) {
        escrever("O mapa possui " + mapaOrdenadoAVL.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(mapaOrdenadoAVL.entrySet().toString());
    }
}
