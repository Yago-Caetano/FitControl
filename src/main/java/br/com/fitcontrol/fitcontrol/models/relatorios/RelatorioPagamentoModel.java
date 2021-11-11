package br.com.fitcontrol.fitcontrol.models.relatorios;

import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;

import java.sql.Date;

public class RelatorioPagamentoModel extends RelatorioModel {
    private Date data;
    private String funcionario;
    private String cliente;
    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
