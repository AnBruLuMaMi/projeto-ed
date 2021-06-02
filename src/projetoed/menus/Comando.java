package projetoed.menus;

import java.util.ArrayList;

interface Function {
    void run(ArrayList<String> parameters);
}

public class Comando {
    private String pattern;
    private Function metodo;
    private ArrayList<String> parameters;

    public Comando(String pattern, Function metodo) {
        this.pattern = pattern;
        this.metodo = metodo;
        this.parameters = new ArrayList<>();
    }

    public String getPattern() {
        return pattern;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    public void executar() {
        this.metodo.run(parameters);
    }
}
