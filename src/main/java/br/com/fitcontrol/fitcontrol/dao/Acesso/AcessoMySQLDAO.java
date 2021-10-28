package br.com.fitcontrol.fitcontrol.dao.Acesso;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessoMySQLDAO  <E extends Entidade> extends MySQLDAO {

    public AcessoMySQLDAO() {
        super(AcessoModel.class);
        super.Has_Status=false;
        setTabela("tbAcesso");
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        AcessoModel acesso=(AcessoModel)entidade;
        String SQL= "Insert into " + getTabela() + "(_Data,tipo,idFuncionario,idCliente,idCatraca) values (?,?,?,?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        AcessoModel acesso=(AcessoModel)entidade;
        String SQL= "Update " + getTabela() + " Set _Data=?,tipo=?,idFuncionario=?,idCliente=?,idCatraca=? where id ="+acesso.getId();
        return SQL;
    }

    @Override
    protected void PrepareStatementInsertUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        AcessoModel acesso=(AcessoModel)entidade;
        stmt.setDate(1,acesso.getData());
        stmt.setByte(2,acesso.getTipo());
        stmt.setInt(3,acesso.getIdFuncionario());
        stmt.setInt(4,acesso.getIdCliente());
        stmt.setInt(5,acesso.getIdCatraca());

    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        AcessoModel acessoModel = new AcessoModel();
        try {
            acessoModel.setId(rs.getInt("id"));
            acessoModel.setData(rs.getDate("_Data"));
            acessoModel.setTipo(rs.getByte("tipo"));
            acessoModel.setIdFuncionario(rs.getInt("idFuncionario"));
            acessoModel.setIdCliente(rs.getInt("idCliente"));
            acessoModel.setIdCatraca(rs.getInt("idCatraca"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)acessoModel;
    }
}
