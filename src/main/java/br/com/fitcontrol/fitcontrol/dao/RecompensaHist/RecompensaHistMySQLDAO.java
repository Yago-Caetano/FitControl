package br.com.fitcontrol.fitcontrol.dao.RecompensaHist;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.RecompensaHistModel;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecompensaHistMySQLDAO  <E extends Entidade> extends MySQLDAO {

    public RecompensaHistMySQLDAO() {
        super(RecompensaHistModel.class);
        super.Has_Status=false;
        setTabela("tbRecompensaHist");
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        String SQL= "Insert into " + getTabela() + "(_Data,Pontos,Titulo,IdCliente) values (?,?,?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        RecompensaHistModel recom=(RecompensaHistModel)entidade;
        String SQL= "Update " + getTabela() + " Set _Data=?,Pontos=?,Titulo=?,IdCliente=? where id ="+recom.getId();
        return SQL;
    }

    @Override
    protected void PrepareStatementInsertUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        RecompensaHistModel recom=(RecompensaHistModel)entidade;
        stmt.setDate(1,recom.getData());
        stmt.setInt(2,recom.getPontos());
        stmt.setString(2,recom.getTitulo());
        stmt.setInt(2,recom.getIdCliente());
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        RecompensaHistModel recom = new RecompensaHistModel();
        try {

            recom.setId(rs.getInt("id"));
            recom.setData(rs.getDate("_Data"));
            recom.setPontos(rs.getInt("Pontos"));
            recom.setTitulo(rs.getString("Titulo"));
            recom.setIdCliente(rs.getInt("IdCliente"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)recom;
    }
}
