package br.com.fitcontrol.fitcontrol.dao.Pagamento;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ItensRecompensaModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagamentosMySQLDAO <E extends Entidade> extends MySQLDAO {


    public PagamentosMySQLDAO() {
        super(PagamentoModel.class);
        super.Has_Status=false;
        setTabela("tbPagamentos");
    }

    @Override
    protected String getInsertCommand(Entidade entidade) {
        String SQL= "Insert into " + getTabela() + "(id,_Data,idCliente,idFuncionario,Valor) values (?,?,?,?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        PagamentoModel pagamento=(PagamentoModel)entidade;
        String SQL= "Update " + getTabela() + " Set _Data=?,idCliente=?,idFuncionario=?,Valor=? where id ="+pagamento.getId();
        return SQL;
    }

    @Override
    protected void PrepareStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {

        PagamentoModel pagamento=(PagamentoModel)entidade;
        stmt.setString(1,pagamento.getId());
        stmt.setDate(2,pagamento.getData());
        stmt.setString(3,pagamento.getIdCliente());
        stmt.setString(4,pagamento.getIdFuncionario());
        stmt.setDouble(5,pagamento.getValor());

    }

    @Override
    protected void PrepareStatementUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        PagamentoModel pagamento=(PagamentoModel)entidade;
        stmt.setDate(1,pagamento.getData());
        stmt.setString(2,pagamento.getIdCliente());
        stmt.setString(3,pagamento.getIdFuncionario());
        stmt.setDouble(4,pagamento.getValor());
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        PagamentoModel pagamento = new PagamentoModel();
        try {
            pagamento.setId(rs.getString("id"));
            pagamento.setData(rs.getDate("_Data"));
            pagamento.setIdCliente(rs.getString("idCliente"));
            pagamento.setIdFuncionario(rs.getString("idFuncionario"));
            pagamento.setValor(rs.getDouble("Valor"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)pagamento;
    }
}
