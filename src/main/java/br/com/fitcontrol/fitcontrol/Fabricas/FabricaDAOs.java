package br.com.fitcontrol.fitcontrol.Fabricas;

import br.com.fitcontrol.fitcontrol.Enums.EntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;
import br.com.fitcontrol.fitcontrol.dao.Usuario.UsuarioMySQLDAO;
import main.java.br.com.fitcontrol.fitcontrol.Enums.TipoRepositorio;

public class FabricaDAOs {
    public static br.com.fitcontrol.fitcontrol.dao.PadraoDAO  Fabrica(EntidadesDisponiveis enumEntidade, TipoRepositorio repositorio) {
        switch (repositorio)
        {
            case MYSQL:
               return montaDAOMySQL(enumEntidade);
            default:
                return null;
        }

    }

    private static PadraoDAO montaDAOMySQL(EntidadesDisponiveis enumEntidade) {
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
