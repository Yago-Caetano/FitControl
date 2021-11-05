package br.com.fitcontrol.fitcontrol.Acesso;

public class FuncionarioLogado {
    private static String nome;

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        FuncionarioLogado.nome = nome;
    }

}
