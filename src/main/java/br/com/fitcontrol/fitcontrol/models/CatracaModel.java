package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

public class CatracaModel extends Entidade {

    private String modelo;
    private String nome;
    private byte tipo;
    private byte status;

    public CatracaModel()
    {
        this.setTipoEntidade(EnumEntidadesDisponiveis.CATRACA);
    }

    public CatracaModel(int id, String modelo) {
        this.modelo = modelo;
        this.setTipoEntidade(EnumEntidadesDisponiveis.CATRACA);
        super.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte getTipo() {
        return tipo;
    }

    public void setTipo(byte tipo) {
        this.tipo = tipo;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
