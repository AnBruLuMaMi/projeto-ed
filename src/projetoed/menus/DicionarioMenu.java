package projetoed.menus;

import projetoed.estruturas.dicionario.Dicionario;
import projetoed.estruturas.listanodos.interfaces.INodo;
import projetoed.estruturas.mapa.HashTable;

import java.util.ArrayList;
import java.util.Map;

public class DicionarioMenu extends MenuEstrutura {
    private Dicionario<String, String> dicionario;

    private HashTable<String, Map.Entry<String, String>> variaveisRef;
    private int varNum;

    public DicionarioMenu() {
        super("Dicionário");
        dicionario = new Dicionario<>();

        variaveisRef = new HashTable<>();
        varNum = 0;

        paragrafos.add("Assim como mapas, um dicionário armazena elementos pares chave-valor(k,v), onde as chaves e valores podem ser de qualquer tipo, porém no caso " +
                "dos dicionários podem haver multiplos valores com uma mesma chave.");

        opcoes.add(new OpcaoMenu("put(k,v)", "Insere um elemento com chave 'k' e valor 'v' no dicionário e retorna o elemento criado"));
        opcoes.add(new OpcaoMenu("remove(e)", "Remove do dicionário um elemento 'e', retornando o elemento removido se não remover nada retorna null"));
        opcoes.add(new OpcaoMenu("get(k)", "Se o dicionário contém elementos com a chave igual a 'k', retorna o primeiro elemento inserido, caso contrário retorna null"));
        opcoes.add(new OpcaoMenu("getAll(k)", "Retorna uma coleção iterável com todos os elementos com a chave igual a 'k' "));
        opcoes.add(new OpcaoMenu("entrySet()", "Retorna uma coleção  iterável dos elementos chave-valor dentro do dicionário"));
        opcoes.add(new OpcaoMenu("isEmpty()", "Retorna se o dicionário está vazio ou não"));
        opcoes.add(new OpcaoMenu("size()", "Retorna o número de elementos armazenados no dicionário"));
        opcoes.add(new OpcaoMenu("toString()", "Exibe o dicionário"));

        comandos.add(new Comando("^put\\s*\\(\\s*(-?\\d+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> put(params)));
        comandos.add(new Comando("^put\\s*\\(\\s*(-?\\d+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> put(params)));
        comandos.add(new Comando("^put\\s*\\(\\s*\"(.*)\"\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> put(params)));
        comandos.add(new Comando("^put\\s*\\(\\s*\"(.*)\"\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> put(params)));
        comandos.add(new Comando("^remove\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> remove(params)));
        comandos.add(new Comando("^get\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> get(params)));
        comandos.add(new Comando("^get\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> get(params)));
        comandos.add(new Comando("^getAll\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> getAll(params)));
        comandos.add(new Comando("^getAll\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> getAll(params)));
        comandos.add(new Comando("^entrySet\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> entrySet(params)));
        comandos.add(new Comando("^isEmpty\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> isEmpty(params)));
        comandos.add(new Comando("^size\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> size(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }

    void armazenarRef(Map.Entry<String, String> nodoRef) {
        String nomeVar = "elemento" + varNum;
        varNum++;

        variaveisRef.put(nomeVar, nodoRef);
        escrever("A referência dessa entrada foi armazenada na váriavel '" + nomeVar + "' para uso posterior");
    }

    void put(ArrayList<String> params) {
        String chave = params.get(0);
        String valor = params.get(1);

        var entrada = dicionario.put(chave, valor);

        escrever("A entrada (" + chave + "," + valor + ") foi armazenada");
        armazenarRef(entrada);
    }

    void remove(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var nodoRef = variaveisRef.get(nomeVariavel);

        if (nodoRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        var entrada = dicionario.remove(nodoRef);
        escrever("A entrada '" + entrada + "' foi removida do dicionário");

        variaveisRef.remove(nomeVariavel);
    }

    void get(ArrayList<String> params) {
        String chave = params.get(0);

        var entradaObtida = dicionario.get(chave);

        if (entradaObtida != null)
            escrever("O primeiro valor que foi armazenado na chave '" + chave + "' é '" + entradaObtida.getValue() + "'");
        else
            escrever("Não existe entrada com a chave '" + chave + "'");

        armazenarRef(entradaObtida);
    }

    void getAll(ArrayList<String> params) {
        String chave = params.get(0);

        var entradas = dicionario.getAll(chave);

        if (entradas != null) {
            String valores = "";

            for (var entrada :
                    entradas) {
                valores += entrada.getValue() + ",";
            }

            valores = valores.substring(0, valores.length() - 1);

            escrever("A entrada com a chave '" + chave + "' possui os valores '[" + valores + "]' armazenados");
        } else {
            escrever("Não existe entrada com a chave '" + chave + "'");
        }
    }

    void entrySet(ArrayList<String> params) {
        escrever("As entradas armazenadas são: " + dicionario.entrySet().toString());
    }

    void isEmpty(ArrayList<String> params) {
        if (dicionario.isEmpty())
            escrever("O dicionário está vazio!");
        else
            escrever("O dicionário não está vazio!");
    }

    void size(ArrayList<String> params) {
        escrever("O dicionário possui " + dicionario.size() + " elementos.");
    }

    void visualizar(ArrayList<String> params) {
        escrever(dicionario.entrySet().toString());
    }
}
