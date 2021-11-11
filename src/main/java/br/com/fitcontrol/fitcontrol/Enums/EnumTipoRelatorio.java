package br.com.fitcontrol.fitcontrol.Enums;

public enum EnumTipoRelatorio {

    ALUNO("Relatório de aluno"),
    FUNCIONARIO ("Relatório de funcionário"),
    PAGAMENTO("Relatório de pagamento");

    private String descricao;

    private EnumTipoRelatorio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
