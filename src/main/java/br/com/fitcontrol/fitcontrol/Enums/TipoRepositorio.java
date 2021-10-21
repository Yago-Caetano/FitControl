package main.java.br.com.fitcontrol.fitcontrol.Enums;

public enum TipoRepositorio {
    MYSQL("MySQL");

    private String descricao;

    private TipoRepositorio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
