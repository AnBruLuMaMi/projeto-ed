package projetoed.menus;

import projetoed.menus.interfaces.IMenu;

public class MenuPrincipal extends Menu {
    public MenuPrincipal() {
        super("Estruturas de Dados", false, "Encerrar");

        opcoes.add(new OpcaoMenu(1, "TAD-Lista Arranjo", "", () -> exibirMenuOpcao(1)));
        opcoes.add(new OpcaoMenu(2, "TAD-Pilha", "", () -> exibirMenuOpcao(2)));
        opcoes.add(new OpcaoMenu(3, "TAD-Fila", "", () -> exibirMenuOpcao(3)));
        opcoes.add(new OpcaoMenu(4, "TAD-Lista de Nodos", "", () -> exibirMenuOpcao(4)));
        opcoes.add(new OpcaoMenu(5, "TAD-Árvore Genérica", "", () -> exibirMenuOpcao(5)));
        opcoes.add(new OpcaoMenu(6, "TAD-Árvore Binária", "", () -> exibirMenuOpcao(6)));
        opcoes.add(new OpcaoMenu(7, "TAD-Fila de Prioridade", "", () -> exibirMenuOpcao(7)));
        opcoes.add(new OpcaoMenu(8, "TAD-Mapa", "", () -> exibirMenuOpcao(8)));
        opcoes.add(new OpcaoMenu(9, "TAD-Dicionário", "", () -> exibirMenuOpcao(9)));
        opcoes.add(new OpcaoMenu(10, "TAD-Mapa Ordenado – ABB", "", () -> exibirMenuOpcao(10)));
        opcoes.add(new OpcaoMenu(11, "TAD-Mapa Ordenado – AVL", "", () -> exibirMenuOpcao(11)));
        opcoes.add(new OpcaoMenu(12, "TAD-Grafos", "", () -> exibirMenuOpcao(12)));

        mensagensInputOpcao.add("Selecione o número correspondente da estrutura que deseja testar: ");
    }

    void exibirMenuOpcao(int opcao) {
        IMenu menuOpcao = MenuFactory.create(opcao);
        menuOpcao.exibir();
    }
}
