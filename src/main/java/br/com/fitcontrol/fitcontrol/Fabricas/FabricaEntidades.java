package br.com.fitcontrol.fitcontrol.Fabricas;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

public class FabricaEntidades {
    public static Entidade Fabrica(EnumEntidadesDisponiveis enumEntidade) {
        Entidade retorno;
        switch (enumEntidade)
        {
           // case USUARIO:
                //retorno = new Usuario();
               // break;
            //case FUNCIONARIO:
                //retorno = new FuncionarioModel();
               // break;
            default:
                retorno = new Entidade();
                break;
        }
        return retorno;
    }

}
