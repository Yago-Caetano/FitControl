package br.com.fitcontrol.fitcontrol.dao.Itens_Recompensa;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;
import br.com.fitcontrol.fitcontrol.models.ItensRecompensaModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItensRecompensaMySQLDAO <E extends Entidade> extends MySQLDAO {


    public ItensRecompensaMySQLDAO() {
        super(ItensRecompensaModel.class);
        super.Has_Status=false;
        setTabela("tbItensRecompensa");
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        String SQL= "Insert into " + getTabela() + "(Descricao,Foto) values (?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        ItensRecompensaModel Item=(ItensRecompensaModel)entidade;
        String SQL= "Update " + getTabela() + " Set Descricao=?,Foto=? where id ="+Item.getId();
        return SQL;
    }

    @Override
    protected void PrepareStatementInsertUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {

        ItensRecompensaModel Item=(ItensRecompensaModel)entidade;
        stmt.setString(1,Item.getDescricao());
        stmt.setString(2,Item.getFoto());

    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        ItensRecompensaModel Item = new ItensRecompensaModel();
        try {
            Item.setId(rs.getInt("id"));
            Item.setDescricao(rs.getString("Descricao"));
            Item.setFoto(rs.getString("Foto"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)Item;
    }
}
