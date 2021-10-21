package main.java.br.com.fitcontrol.fitcontrol.Enums;

public enum EntidadesDisponiveis {
    USUARIO("usuario"),
    PEDIDO("pedido"),
    PRODUTO("produto");

    private String descricao;

    private EntidadesDisponiveis(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
