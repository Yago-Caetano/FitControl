package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;

public class FuncionarioModel  extends UsuarioModel {


    public FuncionarioModel(int id, String nome,int nivel) {
        super(id,nome,(byte)nivel);
    }
    public FuncionarioModel()
    {}
}
