package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;

import java.util.Date;

public class AcessoModel extends Entidade {
    private java.sql.Date data;
    private byte tipo;
    private int idCliente;
    private int idFuncionario;
    private int idCatraca;

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCatraca() {
        return idCatraca;
    }

    public void setIdCatraca(int idCatraca) {
        this.idCatraca = idCatraca;
    }
}
