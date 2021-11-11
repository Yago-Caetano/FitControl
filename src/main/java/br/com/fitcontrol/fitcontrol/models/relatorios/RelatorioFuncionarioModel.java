package br.com.fitcontrol.fitcontrol.models.relatorios;

import java.sql.Date;

public class RelatorioFuncionarioModel extends RelatorioModel {
    private Date data;
    private String funcionario;
    private String catraca;

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

    public String getCatraca() {
        return catraca;
    }

    public void setCatraca(String catraca) {
        this.catraca = catraca;
    }
}
