package br.com.fitcontrol.fitcontrol.dao.Usuario;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.UsuarioModel;

import java.sql.*;

public class UsuarioMySQLDAO <E extends Entidade> extends MySQLDAO {
    public UsuarioMySQLDAO() {
        super(UsuarioModel.class);
        super.Has_Status=true;
        setTabela("tbUsuarios");
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        UsuarioModel entidade = new UsuarioModel();
        try {
            entidade.setId(rs.getString("id"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setTelefone(rs.getString("Telefone"));
            entidade.setLogin(rs.getString("Email"));
            entidade.setSenha(rs.getString("Senha"));
            entidade.setStatus(rs.getByte("_Status"));
            entidade.setNivel(rs.getByte("NivelAcesso"));
        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entidade;
    }

    public UsuarioModel login(String _login,String _senha) throws SQLException {
        UsuarioModel user = null;

        try (Connection conexao = ConexaoMySQL.GetConexaoBD()) {
            String SQL = "select * from " + getTabela() + " where Email = ? and Senha = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, _login);
                stmt.setString(2, _senha);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()){
                        user = (UsuarioModel) preencheEntidade(rs);
                    }
                }
            }
        }
        return user;
    }


    @Override
    protected String getInsertCommand(Entidade entidade) {
        UsuarioModel user=(UsuarioModel)entidade;
        String SQL= "Insert into " + getTabela() + "(id,Nome,Telefone,Email,Senha,_Status,NivelAcesso) values (?,?,?,?,?,?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        UsuarioModel user=(UsuarioModel)entidade;
        String SQL= "Update " + getTabela() + " Set Nome=?,Telefone=?,Email=?,Senha=?,_Status=?,NivelAcesso=? where id="+ user.getId();
        return SQL;
    }

    @Override
    protected void PrepareStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        UsuarioModel user = (UsuarioModel) entidade;
        stmt.setString(1,user.getId());
        stmt.setString(2,user.getNome());
        stmt.setString(3,user.getTelefone());
        stmt.setString(4,user.getLogin());
        stmt.setString(5,user.getSenha());
        stmt.setByte(6,(byte)1);
        stmt.setByte(7,user.getNivel());
    }

    @Override
    protected void PrepareStatementUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        UsuarioModel user = (UsuarioModel) entidade;
        stmt.setString(1,user.getNome());
        stmt.setString(2,user.getTelefone());
        stmt.setString(3,user.getLogin());
        stmt.setString(4,user.getSenha());
        stmt.setByte(5,(byte)1);
        stmt.setByte(6,user.getNivel());
    }


}
