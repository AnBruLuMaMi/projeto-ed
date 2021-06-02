package projetoed.menus;

import projetoed.menus.interfaces.IMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Menu implements IMenu {
    private int tamanhoJanela = 80;
    protected String titulo;
    private boolean exibirCabecalhoOperacoes;
    protected String textoVoltar;


    protected ArrayList<String> paragrafos = new ArrayList<>();
    protected ArrayList<OpcaoMenu> opcoes = new ArrayList<>();
    protected ArrayList<String> mensagensInputOpcao = new ArrayList<>();

    public Menu(String titulo, boolean exibirCabecalhoOperacoes, String textoVoltar) {
        this.titulo = titulo;
        this.exibirCabecalhoOperacoes = exibirCabecalhoOperacoes;
        this.textoVoltar = textoVoltar;
    }

    protected void escreverTituloMenu(String titulo) {
        for (int i = 1; i <= tamanhoJanela; i++)
            System.out.print("=");

        System.out.println();

        int posicaoMeio = (tamanhoJanela - titulo.length()) / 2;

        for (int i = 1; i <= posicaoMeio; i++)
            System.out.print(" ");

        for (int i = 0; i < titulo.length(); i++)
            System.out.print(titulo.charAt(i));

        for (int i = 1; i <= posicaoMeio; i++)
            System.out.print(" ");

        System.out.println();

        for (int i = 1; i <= tamanhoJanela; i++)
            System.out.print("=");

        System.out.println();
    }

    protected void escrever(String texto) {
        escrever(texto, false);
    }

    protected void escrever(String texto, boolean ehParagrafo) {
        String[] palavras = texto.split(" ");

        int posicaoX = 1;

        for (int i = 0; i < palavras.length; i++) {
            String palavraAtual = palavras[i] + " ";

            if (i == 0 && ehParagrafo) {
                System.out.print("  ");
                posicaoX += 2;
            }

            if ((posicaoX + palavraAtual.length()) > tamanhoJanela) {
                System.out.println();
                posicaoX = 1;
            }

            System.out.print(palavraAtual);
            posicaoX += palavraAtual.length();
        }

        System.out.println();
    }

    protected void listarOperacoes() {
        if (exibirCabecalhoOperacoes) {
            for (int i = 1; i <= tamanhoJanela; i++)
                System.out.print("-");

            System.out.println();

            escrever("Operações");

            for (int i = 1; i <= tamanhoJanela; i++)
                System.out.print("-");

            System.out.println();
        }

        for (OpcaoMenu op:
                opcoes) {
            String s = "";

            if (op.getId() == -1) {
                s += "- ";
            } else {
                s += op.getId() + ". ";
            }

            s += op.getNome();

            if (!op.getDescricao().isEmpty())
                s += ": ";

            s += op.getDescricao();

            escrever(s);
        }

        System.out.println();
    }

    protected OpcaoMenu obterOperacao(int id) {
        return opcoes.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    protected void pressioneEnterParaContinuar() {
        escrever("Pressione ENTER para continuar...");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exibir() {
        while (true) {
            escreverTituloMenu(titulo);

            for (String paragrafo : paragrafos) {
                escrever(paragrafo, true);
            }

            listarOperacoes();
            escrever("0. " + textoVoltar);

            System.out.println();

            for (String mensagem:
                    mensagensInputOpcao) {
                escrever(mensagem);
            }

            while (true) {
                try {
                    Scanner scan = new Scanner(System.in);
                    int opcaoSelecionada = scan.nextInt();

                    OpcaoMenu operacao = obterOperacao(opcaoSelecionada);

                    if (opcaoSelecionada == 0)
                        System.exit(0);

                    if (operacao == null) {
                        escrever("A operação selecionada não é válida. Tente novamente:");
                        continue;
                    }

                    operacao.executar();
                    break;
                } catch (InputMismatchException e) {
                    escrever("A operação selecionada não é válida. Tente novamente:");
                    continue;
                }
            }
        }
    }
}
