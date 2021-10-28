package main.java.br.com.fitcontrol.fitcontrol.Enums;

public enum EnumTipoRepositorio {
    MYSQL("MySQL");

    private String descricao;

    private EnumTipoRepositorio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
