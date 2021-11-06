package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

public class FuncionarioModel  extends UsuarioModel {


    public FuncionarioModel(String id, String nome,int nivel) {
        super(id,nome,(byte)nivel);
        this.setTipoEntidade(EnumEntidadesDisponiveis.FUNCIONARIO);

    }
    public FuncionarioModel()
    {        this.setTipoEntidade(EnumEntidadesDisponiveis.FUNCIONARIO);

    }
}
