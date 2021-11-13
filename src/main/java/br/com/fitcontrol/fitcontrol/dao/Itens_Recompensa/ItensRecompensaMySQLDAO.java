package br.com.fitcontrol.fitcontrol.dao.Itens_Recompensa;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ItensRecompensaModel;

import java.sql.*;

public class ItensRecompensaMySQLDAO <E extends Entidade> extends MySQLDAO {


    public ItensRecompensaMySQLDAO() {
        super(ItensRecompensaModel.class);
        super.Has_Status=false;
        setTabela("tbItensRecompensa");
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        String SQL= "Insert into " + getTabela() + "(id,Descricao,Foto) values (?,?,?)";
        return SQL;
    }

    @Override
    public void Insert(Entidade entidade) throws SQLException {
        ItensRecompensaModel item = (ItensRecompensaModel)entidade;
        Connection conn = null;
        try  {
            conn = ConexaoMySQL.GetConexaoBD();
            conn.setAutoCommit(false);
            String SQL = getInsertCommand((E) entidade);
            entidade.setId();
            PreparedStatement stmt = conn.prepareStatement(SQL);
            this.PrepareStatementInsert((E) entidade,stmt);
            stmt.execute();

            SQL="Insert into tbItensXRecompensa (idItens,idRecompensa) values (?,?)";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1,item.getId());
            stmt.setInt(2,item.getIdRecompensa());
            stmt.execute();

            conn.commit();
            conn.close();


        } catch (SQLException throwables) {
            conn.rollback();
            throwables.printStackTrace();
            conn.close();
        }
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        ItensRecompensaModel Item=(ItensRecompensaModel)entidade;
        String SQL= "Update " + getTabela() + " Set Descricao=?,Foto=? where id ='"+Item.getId()+"'";
        return SQL;
    }

    @Override
    protected void PrepareStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {

        ItensRecompensaModel Item=(ItensRecompensaModel)entidade;
        stmt.setString(1,Item.getDescricao());
        stmt.setString(2,Item.getDescricao());
        stmt.setString(3,Item.getFoto());

    }

    @Override
    protected void PrepareStatementUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        ItensRecompensaModel Item=(ItensRecompensaModel)entidade;
        stmt.setString(1,Item.getDescricao());
        stmt.setString(2,Item.getFoto());

    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        ItensRecompensaModel Item = new ItensRecompensaModel();
        try {
            Item.setId(rs.getString("id"));
            Item.setDescricao(rs.getString("Descricao"));
            Item.setFoto(rs.getString("Foto"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)Item;
    }
}
