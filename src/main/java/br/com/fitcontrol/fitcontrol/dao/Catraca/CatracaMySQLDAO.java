package br.com.fitcontrol.fitcontrol.dao.Catraca;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatracaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public CatracaMySQLDAO() {
        super(CatracaModel.class);
        super.Has_Status=true;
        setTabela("tbCatracas");
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        CatracaModel catraca=(CatracaModel)entidade;
        String SQL= "Insert into " + getTabela() + "(id,Nome,Modelo,Tipo,_Status) values (?,?,?,?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        CatracaModel catraca=(CatracaModel)entidade;
        String SQL= "Update " + getTabela() + " Set Nome=?,Modelo=?,Tipo=?,_Status=? where id="+ catraca.getId();
        return SQL;
    }

    @Override
    protected void PrepareStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        CatracaModel catraca = (CatracaModel) entidade;
        stmt.setString(1, catraca.getId());
        stmt.setString(2,catraca.getNome());
        stmt.setString(3,catraca.getModelo());
        stmt.setByte(4,catraca.getTipo());
        stmt.setByte(5,catraca.getStatus());
    }
    @Override
    protected void PrepareStatementUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        CatracaModel catraca = (CatracaModel) entidade;
        stmt.setString(1,catraca.getNome());
        stmt.setString(2,catraca.getModelo());
        stmt.setByte(3,catraca.getTipo());
        stmt.setByte(4,catraca.getStatus());

    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        CatracaModel catraca = new CatracaModel();
        try {
            catraca.setId(rs.getString("id"));
            catraca.setNome(rs.getString("Nome"));
            catraca.setModelo(rs.getString("Modelo"));
            catraca.setTipo(rs.getByte("Tipo"));
            catraca.setStatus(rs.getByte("_Status"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)catraca;
    }
}
