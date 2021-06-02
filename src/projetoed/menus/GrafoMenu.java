package projetoed.menus;

import projetoed.estruturas.arvorebinaria.ArvoreBinaria;
import projetoed.estruturas.grafo.Aresta;
import projetoed.estruturas.grafo.Grafo;
import projetoed.estruturas.grafo.Vertice;
import projetoed.estruturas.grafo.exception.ArestaExisteException;
import projetoed.estruturas.listanodos.exceptions.NodoInvalidoException;
import projetoed.estruturas.listanodos.interfaces.INodo;
import projetoed.estruturas.mapa.HashTable;

import java.util.ArrayList;

public class GrafoMenu extends MenuEstrutura {
    private Grafo<String> grafo;
    private HashTable<String, Vertice<String>> verticesRef;
    private HashTable<String, Aresta<String>> arestasRef;
    private int varNumVertice;
    private int varNumAresta;

    public GrafoMenu() {
        super("Grafos");
        grafo = new Grafo<>();
        verticesRef = new HashTable<>();
        arestasRef = new HashTable<>();
        varNumVertice = 0;
        varNumAresta = 0;

        paragrafos.add("O TAD Grafo é um conjunto de elementos que são armazenados em vértices ou arestas, ou até mesmo em ambos dentro dos grafos.");
        paragrafos.add("Para inserir um elemento no vértice deve-se usar o método insertVertex(x) e assim retorna um novo vértice para armazenar um elemento x. Para remover o " +
                "elemento do vértice é necessário usar o método removeVertex(v). E por fim, para visualizar todos os elementos dos vértices é preciso usar o o método vertices().");
        paragrafos.add("Para inserir um elemento na aresta, é necessário usar o método insertEdge(v,w,x) que insere e retorna uma nova aresta não dirigida (v,w) e armazena o " +
                "elemento x. Para remove-lo da aresta basta usar o método removeEdge(e). E para visualizar os elementos das arestas é preciso usar o método edges().");

        opcoes.add(new OpcaoMenu("vertices()", "Retorna um conjunto de todos os vértices do grafo"));
        opcoes.add(new OpcaoMenu("edges()", "Retorna um conjunto de todas as arestas do grafo"));
        opcoes.add(new OpcaoMenu("incidentEdges(v)", "Retorna um conjunto das arestas incidentes sob o vértice 'v'. "));
        opcoes.add(new OpcaoMenu("opposite(v,e)", "Retorna o vértice final da aresta 'e' oposto ao vértice 'v'; um erro ocorre se 'e' não é incidente a 'v'."));
        opcoes.add(new OpcaoMenu("endVertices(e)", "Retorna um arranjo armazenando os vértices finais da aresta 'e'."));
        opcoes.add(new OpcaoMenu("areAdjacent(v,w)", "Testa se os vértices 'v' e 'w' são adjacentes."));
        opcoes.add(new OpcaoMenu("replace(v,x)", "Substitui o elemento armazenado no nodo 'v' com 'x'."));
        opcoes.add(new OpcaoMenu("replace(e, x)", "Substitui o elemento armazenado na aresta 'e' com 'x'. "));
        opcoes.add(new OpcaoMenu("insertVertex(x)", "Insere e retorna um novo vértice armazenando o elemento 'x'."));
        opcoes.add(new OpcaoMenu("insertEdge(v,w,x)", "Insere e retorna uma nova aresta não dirigida (v, w) e armazena o elemento 'x'"));
        opcoes.add(new OpcaoMenu("removeVertex(v)", "Remove o vértice 'v' e todas as arestas incidentes e retorna o elemento armazenado em 'v'."));
        opcoes.add(new OpcaoMenu("removeEdge(e)", "Remove a aresta 'e' e retorna o elemento armazenado em 'e'."));
        opcoes.add(new OpcaoMenu("toString()", "Exibe o grafo"));

        comandos.add(new Comando("^vertices\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> vertices(params)));
        comandos.add(new Comando("^edges\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> edges(params)));
        comandos.add(new Comando("^incidentEdges\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> incidentEdges(params)));
        comandos.add(new Comando("^opposite\\s*\\(\\s*(.+)\\s*,\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> opposite(params)));
        comandos.add(new Comando("^endVertices\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> endVertices(params)));
        comandos.add(new Comando("^areAdjacent\\s*\\(\\s*(.+)\\s*,\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> areAdjacent(params)));
        comandos.add(new Comando("^replace\\s*\\(\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> replace(params)));
        comandos.add(new Comando("^replace\\s*\\(\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> replace(params)));
        comandos.add(new Comando("^insertVertex\\s*\\(\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> insertVertex(params)));
        comandos.add(new Comando("^insertVertex\\s*\\(\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> insertVertex(params)));
        comandos.add(new Comando("^insertEdge\\s*\\(\\s*(.+)\\s*,\\s*(.+)\\s*,\\s*(-?\\d+)\\s*\\)\\s*;*\\s*$", (params) -> insertEdge(params)));
        comandos.add(new Comando("^insertEdge\\s*\\(\\s*(.+)\\s*,\\s*(.+)\\s*,\\s*\"(.*)\"\\s*\\)\\s*;*\\s*$", (params) -> insertEdge(params)));
        comandos.add(new Comando("^removeVertex\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> removeVertex(params)));
        comandos.add(new Comando("^removeEdge\\s*\\(\\s*(.+)\\s*\\)\\s*;*\\s*$", (params) -> removeEdge(params)));
        comandos.add(new Comando("^toString\\s*\\(\\s*\\)\\s*;*\\s*$", (params) -> visualizar(params)));
    }

    void armazenarRefVertice(Vertice<String> vertice) {
        String nomeVar = "vertice" + varNumVertice;
        varNumVertice++;

        verticesRef.put(nomeVar, vertice);
        escrever("A referência desse vertice foi armazenada na váriavel '" + nomeVar + "' para uso posterior");
    }

    void armazenarRefAresta(Aresta<String> aresta) {
        String nomeVar = "aresta" + varNumAresta;
        varNumAresta++;

        arestasRef.put(nomeVar, aresta);
        escrever("A referência dessa aresta foi armazenada na váriavel '" + nomeVar + "' para uso posterior");
    }

    void vertices(ArrayList<String> params) {
        escrever("Os vértices do Grafo são: " + grafo.vertices().toString());
    }

    void edges(ArrayList<String> params) {
        escrever("As arestas do Grafo são: " + grafo.edges().toString());
    }

    void incidentEdges(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var verticeRef = verticesRef.get(nomeVariavel);

        if (verticeRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        escrever("As arestas incidentes do vértice são: " + grafo.incidentEdges(verticeRef).toString());
    }

    void opposite(ArrayList<String> params) {
        String nomeVariavelVertice = params.get(0);
        var verticeRef = verticesRef.get(nomeVariavelVertice);

        if (verticeRef == null) {
            escrever("A váriavel '" + nomeVariavelVertice + "' não existe! Tente novamente!");
            return;
        }

        String nomeVariavelAresta = params.get(1);
        var arestaRef = arestasRef.get(nomeVariavelAresta);

        if (arestaRef == null) {
            escrever("A váriavel '" + nomeVariavelAresta + "' não existe! Tente novamente!");
            return;
        }

        try {
            var verticeRetorno = grafo.opposite(verticeRef, arestaRef);
            escrever("O vertice oposto possui o valor '" + verticeRetorno.toString() + "'");
        } catch (NodoInvalidoException e) {
            escrever("A aresta não é incidente ao vertice");
        }
    }

    void endVertices(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var arestaRef = arestasRef.get(nomeVariavel);

        if (arestaRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        escrever("Os vértices finais da aresta são: " + grafo.endVertices(arestaRef).toString());
    }

    void areAdjacent(ArrayList<String> params) {
        String nomeVariavelVertice = params.get(0);
        var verticeRef = verticesRef.get(nomeVariavelVertice);

        if (verticeRef == null) {
            escrever("A váriavel '" + nomeVariavelVertice + "' não existe! Tente novamente!");
            return;
        }

        String nomeVariavelVertice2 = params.get(1);
        var vertice2Ref = verticesRef.get(nomeVariavelVertice2);

        if (vertice2Ref == null) {
            escrever("A váriavel '" + nomeVariavelVertice2 + "' não existe! Tente novamente!");
            return;
        }

        var saoAdjacente = grafo.areAdjacent(verticeRef, vertice2Ref);

        if (saoAdjacente)
            escrever("Os vértices são adjacentes");
        else
            escrever("Os vértices não são adjacentes");
    }

    void replace(ArrayList<String> params) {
        String elemento = params.get(1);

        String nomeVariavel = params.get(0);
        var verticeRef = verticesRef.get(nomeVariavel);

        if (verticeRef != null) {
            grafo.replace(verticeRef, elemento);
            escrever("O vértice da váriavel '" + nomeVariavel + "' teve seu elemento alterado para '" + elemento +"'");
            return;
        }

        var arestaRef = arestasRef.get(nomeVariavel);

        if (arestaRef != null) {
            grafo.replace(arestaRef, elemento);
            escrever("A aresta da váriavel '" + nomeVariavel + "' teve seu elemento alterado para '" + elemento +"'");
            return;
        }

        escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
    }

    void insertVertex(ArrayList<String> params) {
        String elemento = params.get(0);
        var verticeInserido = grafo.insertVertex(elemento);
        escrever("O vértice com elemento '" + elemento + "' foi inserido");
        armazenarRefVertice(verticeInserido);
    }

    void insertEdge(ArrayList<String> params) {
        String nomeVariavelVertice1 = params.get(0);
        var vertice1Ref = verticesRef.get(nomeVariavelVertice1);

        if (vertice1Ref == null) {
            escrever("A váriavel '" + nomeVariavelVertice1 + "' não existe! Tente novamente!");
            return;
        }

        String nomeVariavelVertice2 = params.get(1);
        var vertice2Ref = verticesRef.get(nomeVariavelVertice2);

        if (vertice2Ref == null) {
            escrever("A váriavel '" + nomeVariavelVertice2 + "' não existe! Tente novamente!");
            return;
        }

        String elemento = params.get(2);

        try {
            var arestaInserida = grafo.insertEdge(vertice1Ref, vertice2Ref, elemento);
            escrever("Foi criada uma aresta para o vértice '" + vertice1Ref + "' e '" + vertice2Ref +"' com o elemento '" + elemento + "'");
            armazenarRefAresta(arestaInserida);
        } catch (ArestaExisteException e) {
            escrever("Já existe esta aresta! Tente novamente com vértices diferentes!");
        }
    }

    void removeVertex(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var verticeRef = verticesRef.get(nomeVariavel);

        if (verticeRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        var elemento = grafo.removeVertex(verticeRef);
        escrever("O vértice com elemento '" + elemento + "' foi removido, juntamente com todas as suas arestas");
        verticesRef.remove(nomeVariavel);
    }

    void removeEdge(ArrayList<String> params) {
        String nomeVariavel = params.get(0);
        var arestaRef = arestasRef.get(nomeVariavel);

        if (arestaRef == null) {
            escrever("A váriavel '" + nomeVariavel + "' não existe! Tente novamente!");
            return;
        }

        var elemento = grafo.removeEdge(arestaRef);
        escrever("A aresta com elemento '" + elemento + "' foi removida");
        arestasRef.remove(nomeVariavel);
    }

    void visualizar(ArrayList<String> params) {
        escrever(grafo.toString());
    }
}
