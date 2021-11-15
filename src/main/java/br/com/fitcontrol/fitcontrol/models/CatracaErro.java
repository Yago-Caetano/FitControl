package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;

public class CatracaErro extends Entidade {

    private String idCatraca;

    private int CodErro;

    public String getIdCatraca() {
        return idCatraca;
    }

    public void setIdCatraca(String idCatraca) {
        this.idCatraca = idCatraca;
    }

    public int getCodErro() {
        return CodErro;
    }

    public void setCodErro(int codErro) {
        CodErro = codErro;
    }
}
