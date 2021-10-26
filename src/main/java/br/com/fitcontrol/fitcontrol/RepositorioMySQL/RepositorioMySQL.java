package br.com.fitcontrol.fitcontrol.RepositorioMySQL;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Enums.EntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaDAOs;
import br.com.fitcontrol.fitcontrol.dao.Usuario.UsuarioMySQLDAO;
import main.java.br.com.fitcontrol.fitcontrol.Enums.TipoRepositorio;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioMySQL  extends Repositorio {


    @Override
    public Entidade localiza(int codigo, EntidadesDisponiveis tipoEntidade) {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        Entidade entidade = null;
        try {
            entidade = dao.localiza(codigo);
        } catch (SQLException ex) {
            //Logger.getLogger(RepositorioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entidade;
    }

    @Override
    public ArrayList<Entidade> GetAll(EntidadesDisponiveis tipoEntidade) throws SQLException {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);

        return  dao.lista();
    }

    @Override
    public Entidade login(String login, String Senha, EntidadesDisponiveis tipoEntidade) {
        if (tipoEntidade.equals(EntidadesDisponiveis.USUARIO))
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
    public void Insert(Entidade entidade,EntidadesDisponiveis tipoEntidade) {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        dao.Insert(entidade);
    }

    @Override
    public void Update(Entidade entidade, EntidadesDisponiveis tipoEntidade) {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        dao.Update(entidade);
    }

    @Override
    public void Delete(Entidade entidade, EntidadesDisponiveis tipoEntidade) {
        PadraoDAO dao = FabricaDAOs.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        dao.Delete(entidade);
    }
}