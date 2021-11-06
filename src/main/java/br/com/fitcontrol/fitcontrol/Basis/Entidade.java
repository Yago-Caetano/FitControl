package br.com.fitcontrol.fitcontrol.Basis;

import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

import java.util.UUID;

public class Entidade {

    private String id;

    private EnumEntidadesDisponiveis TipoEntidade;

    public EnumEntidadesDisponiveis getTipoEntidade() {
        return TipoEntidade;
    }

    public void setTipoEntidade(EnumEntidadesDisponiveis tipoEntidade) {
        TipoEntidade = tipoEntidade;
    }

    private String CreateId()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public Entidade() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setId (){
        this.id = CreateId();
    }

}
