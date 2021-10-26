package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;

public class RecompensaModel  extends Entidade {

    private String Descricao;

    private int PontosNecessarios;

    public RecompensaModel(){}
    public RecompensaModel(Integer id,int Pontos,String Descricao) {
        this.Descricao = Descricao;
        super.setId(id);
        this.PontosNecessarios = Pontos;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public int getPontosNecessarios() {
        return PontosNecessarios;
    }

    public void setPontosNecessarios(int pontosNecessarios) {
        PontosNecessarios = pontosNecessarios;
    }

    @Override
    public String toString() {
        return Descricao;
    }
}
