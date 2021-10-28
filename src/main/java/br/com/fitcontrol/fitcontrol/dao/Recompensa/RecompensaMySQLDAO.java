package br.com.fitcontrol.fitcontrol.dao.Recompensa;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.PontuacaoHistModel;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecompensaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public RecompensaMySQLDAO() {
        super(RecompensaModel.class);
        super.Has_Status=false;
        setTabela("tbRecompensa");
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        String SQL= "Insert into " + getTabela() + "(Titulo,Pontos) values (?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        RecompensaModel recom=(RecompensaModel)entidade;
        String SQL= "Update " + getTabela() + " Set Titulo=?,Pontos=? where id ="+recom.getId();
        return SQL;
    }

    @Override
    protected void PrepareStatementInsertUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        RecompensaModel recom=(RecompensaModel)entidade;
        stmt.setString(1,recom.getDescricao());
        stmt.setInt(2,recom.getPontosNecessarios());

    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        RecompensaModel recom = new RecompensaModel();
        try {

            recom.setId(rs.getInt("id"));
            recom.setDescricao(rs.getString("Titulo"));
            recom.setPontosNecessarios(rs.getInt("Pontos"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)recom;
    }
}
