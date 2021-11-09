package br.com.fitcontrol.fitcontrol.RepositorioMySQL;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaDAOs;
import br.com.fitcontrol.fitcontrol.dao.Usuario.UsuarioMySQLDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioMySQL  extends Repositorio {


    @Override
    public Entidade localiza(String codigo, EnumEntidadesDisponiveis tipoEntidade) {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio.MYSQL);
        Entidade entidade = null;
        try {
            entidade = dao.localiza(codigo);
        } catch (SQLException ex) {
            //Logger.getLogger(RepositorioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entidade;
    }

    @Override
    public ArrayList<Entidade> GetAll(EnumEntidadesDisponiveis tipoEntidade) throws SQLException {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio.MYSQL);

        return  dao.lista();
    }

    @Override
    public Entidade login(String login, String Senha, EnumEntidadesDisponiveis tipoEntidade) {
        if (tipoEntidade.equals(EnumEntidadesDisponiveis.USUARIO))
        {
            UsuarioMySQLDAO dao = new UsuarioMySQLDAO();
            Entidade entidade = null;
            try {
                entidade = dao.login(login,Senha);
            } catch (SQLException ex) {
                //Logger.getLogger(RepositorioMySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return entidade;
        }
        return null;
    }

    @Override
    public void Insert(Entidade entidade, EnumEntidadesDisponiveis tipoEntidade) throws SQLException {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio.MYSQL);
        dao.Insert(entidade);
    }

    @Override
    public void Update(Entidade entidade, EnumEntidadesDisponiveis tipoEntidade) {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio.MYSQL);
            dao.Update(entidade);

    }

    @Override
    public void Delete(Entidade entidade, EnumEntidadesDisponiveis tipoEntidade) {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio.MYSQL);
            dao.Delete(entidade);

    }
}