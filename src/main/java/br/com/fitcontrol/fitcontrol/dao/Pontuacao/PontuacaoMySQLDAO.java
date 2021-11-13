package br.com.fitcontrol.fitcontrol.dao.Pontuacao;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ItensRecompensaModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import br.com.fitcontrol.fitcontrol.models.PontuacaoHistModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PontuacaoMySQLDAO <E extends Entidade> extends MySQLDAO {

    public PontuacaoMySQLDAO() {
        super(PontuacaoHistModel.class);
        super.Has_Status=false;
        setTabela("tbPontuacaoHist");
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        String SQL= "Insert into " + getTabela() + "(id,Pontos,idAcesso,idCliente) values (?,?,?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        PontuacaoHistModel pont=(PontuacaoHistModel)entidade;
        String SQL= "Update " + getTabela() + " Set Pontos=?,idAcesso=?,idCliente=? where id ='"+pont.getId() +"'";
        return SQL;
    }

    @Override
    protected void PrepareStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        PontuacaoHistModel pont=(PontuacaoHistModel)entidade;
        stmt.setString(1,pont.getId());
        stmt.setInt(1,pont.getPontos());
        stmt.setString(2,pont.getIdAcesso());
        stmt.setString(3,pont.getIdCliente());
    }

    @Override
    protected void PrepareStatementUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        PontuacaoHistModel pont=(PontuacaoHistModel)entidade;
        stmt.setInt(1,pont.getPontos());
        stmt.setString(2,pont.getIdAcesso());
        stmt.setString(3,pont.getIdCliente());
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        PontuacaoHistModel pont = new PontuacaoHistModel();
        try {

            pont.setId(rs.getString("id"));
            pont.setPontos(rs.getInt("Pontos"));
            pont.setIdAcesso(rs.getString("idAcesso"));
            pont.setIdCliente(rs.getString("idCliente"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)pont;
    }

}
