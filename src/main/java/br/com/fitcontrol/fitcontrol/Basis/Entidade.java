package br.com.fitcontrol.fitcontrol.Basis;

import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

public class Entidade {

    private int id;

    private EnumEntidadesDisponiveis TipoEntidade;

    public EnumEntidadesDisponiveis getTipoEntidade() {
        return TipoEntidade;
    }

    public void setTipoEntidade(EnumEntidadesDisponiveis tipoEntidade) {
        TipoEntidade = tipoEntidade;
    }

    public Entidade() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
