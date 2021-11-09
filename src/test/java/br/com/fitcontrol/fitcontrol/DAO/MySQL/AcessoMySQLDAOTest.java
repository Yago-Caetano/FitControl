package br.com.fitcontrol.fitcontrol.DAO.MySQL;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;
import br.com.fitcontrol.fitcontrol.dao.Acesso.AcessoMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static br.com.fitcontrol.fitcontrol.DAO.MySQL.ConexaoMySQLTest.VerifyDBTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcessoMySQLDAOTest {

    private ClienteModel _cliente;
    private FuncionarioModel _func;
    private CatracaModel _Catr;
    private AcessoModel acesso;
    @Test
    public void test()
    {
        assertEquals(ConexaoMySQLTest.VerifyDBTest(),true);

    }
    /*
    * Completa o acessoModel, atribuindo os clientes e funcionários e catracas
     */
   private AcessoModel GetAcessoModel()
   {
       AcessoModel _acesso = new AcessoModel();
       _cliente=(ClienteModel) FabricaEntidadeTests.FabricarEntidade(EnumEntidadesDisponiveis.CLIENTE);
       _func=(FuncionarioModel) FabricaEntidadeTests.FabricarEntidade(EnumEntidadesDisponiveis.FUNCIONARIO);
       _Catr=(CatracaModel) FabricaEntidadeTests.FabricarEntidade(EnumEntidadesDisponiveis.CATRACA);
       _acesso=(AcessoModel) FabricaEntidadeTests.FabricarEntidade(EnumEntidadesDisponiveis.ACESSO);
       _acesso.setIdCliente(_cliente.getId());
       _acesso.setIdCatraca(_Catr.getId());
       _acesso.setIdFuncionario(_func.getId());

       return  _acesso;
   }
    @Test
    public void testInsert()
    {
        boolean testeOk=false;
        Connection conn= null;
        try {
            conn = ConexaoMySQL.GetConexaoBD();
            conn.setAutoCommit(false);
            acesso=GetAcessoModel();
            AcessoMySQLDAO dao= new AcessoMySQLDAO();
           // dao.Insert(conn,acesso);

            testeOk=true;
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            testeOk=false;
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }


    protected Entidade PreencherEntidade() {
        AcessoModel acesso = new AcessoModel();
        acesso.setId();
        acesso.setIdCliente("");
        return acesso;
    }
}
