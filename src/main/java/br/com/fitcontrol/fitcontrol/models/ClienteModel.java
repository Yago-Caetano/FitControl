package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

public class ClienteModel  extends UsuarioModel {


    public ClienteModel(int id, String nome, String email, String telefone, int pontos){
        super(id,nome,(byte)1);
        super.setLogin(email);
        super.setTelefone(telefone);
        super.setPontos(pontos);
        this.setTipoEntidade(EnumEntidadesDisponiveis.CLIENTE);


    }

    public ClienteModel(){}
}
