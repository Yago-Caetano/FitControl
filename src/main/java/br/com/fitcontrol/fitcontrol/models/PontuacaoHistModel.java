package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

public class PontuacaoHistModel extends Entidade {

    private int pontos;
    private String idAcesso;
    private String idCliente;

    public PontuacaoHistModel() {
        this.setTipoEntidade(EnumEntidadesDisponiveis.PONTUACAO);

    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(String idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
