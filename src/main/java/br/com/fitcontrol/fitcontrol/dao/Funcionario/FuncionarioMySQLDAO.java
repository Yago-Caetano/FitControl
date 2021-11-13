package br.com.fitcontrol.fitcontrol.dao.Funcionario;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;

import java.sql.*;
import java.util.ArrayList;

public class FuncionarioMySQLDAO  <E extends Entidade> extends MySQLDAO {
    public FuncionarioMySQLDAO() {
        super(FuncionarioModel.class);
        super.Has_Status=true;
        setTabela("tbFuncionarios");
    }

    @Override
    public Entidade localiza(String codigo) throws SQLException {
        FuncionarioModel entidade = null;
        try (Connection conexao = ConexaoMySQL.GetConexaoBD()) {
            String SQL;
            if (Has_Status)
                SQL= "select * from " + getTabela() + " where Id = ? and _Status>0 and NivelAcesso=?";
            else
                SQL= "select * from " + getTabela() + " where Id = ? and NivelAcesso=?";
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, codigo);
                stmt.setInt(2, EnumTipoUsuarios.FUNCIONARIO.getCode());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()){
                        entidade = (FuncionarioModel) preencheEntidade(rs);
                    }
                }
            }
        }
        return entidade;
    }
    public FuncionarioModel login(String _login,String _senha) throws SQLException {
        FuncionarioModel user = null;

        try (Connection conexao = ConexaoMySQL.GetConexaoBD()) {
            String SQL = "select * from " + getTabela() + " where Email = ? and Senha = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, _login);
                stmt.setString(2, _senha);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()){
                        user = (FuncionarioModel) preencheEntidade(rs);
                    }
                }
            }
        }
        return user;
    }
    @Override
    public ArrayList lista() throws SQLException {
        ArrayList<FuncionarioModel> entidades = new ArrayList();
        try (Connection conexao = ConexaoMySQL.GetConexaoBD()) {
            String SQL;
            if (Has_Status)
                SQL= "select * from " + getTabela() + " where _Status > 0 and  NivelAcesso="+ EnumTipoUsuarios.FUNCIONARIO.getCode();
            else
                SQL= "select * from " + getTabela() +" where  NivelAcesso="+ EnumTipoUsuarios.FUNCIONARIO.getCode();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        FuncionarioModel entidade = (FuncionarioModel) preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }

        return entidades;
    }

    @Override
    protected String getInsertCommand(Entidade entidade) {
        FuncionarioModel user=(FuncionarioModel)entidade;
        String SQL= "Insert into " + getTabela() + "(id,Nome,Telefone,Email,Senha,_Status,NivelAcesso) values (?,?,?,?,?,?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        FuncionarioModel user=(FuncionarioModel)entidade;
        String SQL= "Update " + getTabela() + " Set Nome=?,Telefone=?,Email=?,Senha=?,_Status=?,NivelAcesso=? where id='"+ user.getId()+"'";
        return SQL;
    }

    @Override
    protected void PrepareStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        FuncionarioModel user = (FuncionarioModel) entidade;
        stmt.setString(1,user.getId());
        stmt.setString(2,user.getNome());
        stmt.setString(3,user.getTelefone());
        stmt.setString(4,user.getLogin());
        stmt.setString(5,user.getSenha());
        stmt.setByte(6, EnumTipoUsuarios.FUNCIONARIO.getCode());
        stmt.setByte(7,user.getNivel());

    }
    @Override
    protected void PrepareStatementUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        FuncionarioModel user = (FuncionarioModel) entidade;
        stmt.setString(1,user.getNome());
        stmt.setString(2,user.getTelefone());
        stmt.setString(3,user.getLogin());
        stmt.setString(4,user.getSenha());
        stmt.setByte(5, EnumTipoUsuarios.FUNCIONARIO.getCode());
        stmt.setByte(6,user.getNivel());

    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        FuncionarioModel entidade = new FuncionarioModel();
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
}
