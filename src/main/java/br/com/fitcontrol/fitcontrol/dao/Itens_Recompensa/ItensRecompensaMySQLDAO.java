package br.com.fitcontrol.fitcontrol.dao.Itens_Recompensa;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;
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
        String SQL= "Insert into " + getTabela() + "(Descricao,Foto) values (?,?)";
        return SQL;
    }

    @Override
    public void Insert(Entidade entidade) throws SQLException {
        ItensRecompensaModel item = (ItensRecompensaModel)entidade;
        Connection conn = null;
        try  {
            conn = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA);
            conn.setAutoCommit(false);
            String SQL = getInsertCommand((E) entidade);
            PreparedStatement stmt = conn.prepareStatement(SQL);
            PrepareStatementInsertUpdate((E) entidade,stmt);
            stmt.execute();

            SQL="Insert into tbItensXRecompensa (idItens,idRecompensa) values (?,?)";
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,item.getId());
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
