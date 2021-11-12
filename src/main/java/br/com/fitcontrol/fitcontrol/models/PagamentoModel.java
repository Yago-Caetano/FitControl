package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

import java.sql.Date;

public class PagamentoModel  extends Entidade {
    private Date data;
    private double valor;
    private String idCliente;
    private String idFuncionario;
    public PagamentoModel(){
        this.setTipoEntidade(EnumEntidadesDisponiveis.PAGAMENTO);

    }
    public PagamentoModel(String id, Date data, double valor) {
        super.setId(id);
        this.data = data;
        this.valor = valor;
        this.setTipoEntidade(EnumEntidadesDisponiveis.PAGAMENTO);

    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Date getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
