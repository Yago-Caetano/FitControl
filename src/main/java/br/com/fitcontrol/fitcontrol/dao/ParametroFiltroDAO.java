package br.com.fitcontrol.fitcontrol.dao;

public class ParametroFiltroDAO {

    private String Campo;
    private String Valor;
    private String Operador;

    public ParametroFiltroDAO(String Campo, String Valor,String Operador){
        this.Campo = Campo;
        this.Valor = Valor;
        this.Operador = Operador;
    }

    public String getCampo() {
        return Campo;
    }

    public String getValor() {
        return Valor;
    }

    public String getOperador() {
        return Operador;
    }
}
