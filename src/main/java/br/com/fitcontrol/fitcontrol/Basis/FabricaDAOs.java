package main.java.br.com.fitcontrol.fitcontrol.Basis;

import main.java.br.com.fitcontrol.fitcontrol.Enums.EntidadesDisponiveis;
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

    private static br.com.fitcontrol.fitcontrol.dao.PadraoDAO  montaDAOMySQL(EntidadesDisponiveis enumEntidade) {
        br.com.fitcontrol.fitcontrol.dao.PadraoDAO  retorno;
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
