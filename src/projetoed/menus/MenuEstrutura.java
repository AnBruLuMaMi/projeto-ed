package projetoed.menus;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuEstrutura extends Menu {
    ArrayList<Comando> comandos = new ArrayList<>();

    public MenuEstrutura(String titulo) {
        super(titulo, true, "Voltar ao menu principal");

        mensagensInputOpcao.add("Escreva o comando que deseja testar: (Pressione ENTER para voltar)");
    }

    public void exibir() {
        escreverTituloMenu(titulo);

        for (String paragrafo : paragrafos) {
            escrever(paragrafo, true);
        }

        listarOperacoes();

        System.out.println();

        for (String mensagem :
                mensagensInputOpcao) {
            escrever(mensagem);
        }

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("> ");
            String comandoStr = scan.nextLine();

            if (comandoStr.isEmpty()) {
                break;
            }

            Comando comando = parse(comandoStr);

            if (comando == null) {
                escrever("O comando digitado não é válido. Tente novamente!");
                continue;
            }

            comando.executar();
        }
    }

    Comando parse(String comandoStr) {
        for (Comando comando :
                comandos) {
            Pattern pattern = Pattern.compile(comando.getPattern());
            var matcher = pattern.matcher(comandoStr);
            boolean encontrou = matcher.find();

            if (encontrou) {
                ArrayList<String> parametros = new ArrayList<>();

                for (int i = 1; i <= matcher.groupCount(); i++) {
                    parametros.add(matcher.group(i));
                }

                comando.setParameters(parametros);

                return comando;
            }
        }

        return null;
    }
}
