package projetoed.menus;

public class OpcaoMenu {
    private int id;
    private String nome;
    private String descricao;
    private Runnable metodo;

    public OpcaoMenu(String nome, String descricao) {
        this(-1, nome, descricao, () -> System.out.print(""));
    }

    public OpcaoMenu(int id, String nome, String descricao, Runnable metodo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.metodo = metodo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void executar() {
        metodo.run();
    }
}
