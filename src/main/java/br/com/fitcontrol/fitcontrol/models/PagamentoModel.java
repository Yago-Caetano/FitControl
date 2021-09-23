package br.com.fitcontrol.fitcontrol.models;

public class PagamentoModel {
    private int id;
    private String data;
    private double valor;

    public PagamentoModel(int id, String data, double valor) {
        this.id = id;
        this.data = data;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
