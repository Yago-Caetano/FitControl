package br.com.fitcontrol.fitcontrol.Fabricas;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Config.Config;
import br.com.fitcontrol.fitcontrol.RepositorioMySQL.RepositorioMySQL;

public class  FabricaRepositorio {

    public static Repositorio Fabrica() throws Exception {
        if (Config.getInstance().getTipoRepositorio() == main.java.br.com.fitcontrol.fitcontrol.Enums.TipoRepositorio.MYSQL)
            return new RepositorioMySQL();
        else
            throw new Exception("ERRO");
    }
}