package br.com.fitcontrol.fitcontrol.Fabricas;

import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;
import br.com.fitcontrol.fitcontrol.dao.Usuario.UsuarioMySQLDAO;

public class FabricaDAOs {
    public static br.com.fitcontrol.fitcontrol.dao.PadraoDAO  Fabrica(EnumEntidadesDisponiveis enumEntidade, main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio repositorio) {
        switch (repositorio)
        {
            case MYSQL:
               return montaDAOMySQL(enumEntidade);
            default:
                return null;
        }

    }

    private static PadraoDAO montaDAOMySQL(EnumEntidadesDisponiveis enumEntidade) {
        PadraoDAO retorno;
        switch (enumEntidade)
        {
            case USUARIO:
                retorno = new UsuarioMySQLDAO();
                break;
            default:
                retorno = null;
                break;
        }
        return retorno;
    }
}
