package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

import java.util.Date;

public class AcessoModel extends Entidade {
    private java.sql.Date data;
    private byte tipo;
    private String idCliente;
    private String idFuncionario;
    private String idCatraca;

    public AcessoModel() {
        this.setTipoEntidade(EnumEntidadesDisponiveis.ACESSO);
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public java.sql.Date  getData() {
        return data;
    }

    public void setData(java.sql.Date  data) {
        this.data = data;
    }

    public byte getTipo() {
        return tipo;
    }

    public void setTipo(byte tipo) {
        this.tipo = tipo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCatraca() {
        return idCatraca;
    }

    public void setIdCatraca(String idCatraca) {
        this.idCatraca = idCatraca;
    }
}
