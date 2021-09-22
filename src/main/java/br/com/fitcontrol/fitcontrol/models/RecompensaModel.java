package br.com.fitcontrol.fitcontrol.models;

public class RecompensaModel {

    private String Descricao;

    private Double PontosNecessarios;

    private Integer id;

    public RecompensaModel(Integer id,Double Pontos,String Descricao) {
        this.Descricao = Descricao;
        this.id = id;
        this.PontosNecessarios = Pontos;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Double getPontosNecessarios() {
        return PontosNecessarios;
    }

    public void setPontosNecessarios(Double pontosNecessarios) {
        PontosNecessarios = pontosNecessarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Descricao;
    }
}
