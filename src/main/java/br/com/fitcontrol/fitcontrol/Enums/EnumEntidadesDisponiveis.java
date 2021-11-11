package br.com.fitcontrol.fitcontrol.Enums;

public enum EnumEntidadesDisponiveis {
    FUNCIONARIO ("funcionario"),
    CLIENTE ("cliente"),
    CATRACA("catraca"),
    ACESSO("acesso"),
    PONTUACAO("pontuacao"),
    PAGAMENTO("pagamento"),
    ITENS_RECOMPENSA("itens da recompensa"),
    HISTORIC_RECOMPENSA("historico de recompensa"),
    RECOMPENSA("recompensa");


    private String descricao;

    private EnumEntidadesDisponiveis(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
