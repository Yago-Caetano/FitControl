package br.com.fitcontrol.fitcontrol.Enums;

public enum EntidadesDisponiveis {
    USUARIO("funcionario ou cliente"),
    FUNCIONARIO ("funcionario"),
    CLIENTE ("cliente"),
    CATRACA("catraca"),
    ACESSO("acesso"),
    PONTUACAO("pontuacao"),
    PAGAMENTO("pagamento"),
    ITENS_RECOMPENSA("itens da recompensa"),
    RECOMPENSA("recompensa");

    private String descricao;

    private EntidadesDisponiveis(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
