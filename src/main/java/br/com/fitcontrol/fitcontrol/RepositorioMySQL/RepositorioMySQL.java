package br.com.fitcontrol.fitcontrol.RepositorioMySQL;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoRelatorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRelatorios;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaDAOs;
import br.com.fitcontrol.fitcontrol.dao.RelatorioDAO;


import java.sql.Date;
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
    public Entidade login(String login, String Senha) {
            FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();
            Entidade entidade = null;
            try {
                entidade = dao.login(login,Senha);
            } catch (SQLException ex) {
                //Logger.getLogger(RepositorioMySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return entidade;
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

    @Override
    public void GetRelatorio(Date data1, Date data2, EnumTipoRelatorio tiporelatorio,String path) {
        RelatorioDAO dao = FabricaRelatorios.Fabrica(data1,data2,tiporelatorio,main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio.MYSQL);
        dao.GetRelatorio(path);
    }
}