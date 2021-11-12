package br.com.fitcontrol.fitcontrol.dao.Cliente;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.ParametroFiltroDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.UsuarioModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteMySQLDAO<E extends Entidade> extends MySQLDAO {
    public ClienteMySQLDAO() {
        super(ClienteModel.class);
        super.Has_Status=true;
        setTabela("tbUsuarios");
    }

    @Override
    public Entidade localiza(String codigo) throws SQLException {
        ClienteModel entidade = null;
        try (Connection conexao = ConexaoMySQL.GetConexaoBD()) {
            String SQL;
            if (Has_Status)
                SQL= "select * from " + getTabela() + " where Id = ? and _Status>0 and NivelAcesso=?";
            else
                SQL= "select * from " + getTabela() + " where Id = ? and NivelAcesso=?";
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, codigo);
                stmt.setInt(2, EnumTipoUsuarios.CLIENTE.getCode());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()){
                        entidade = (ClienteModel) preencheEntidade(rs);
                    }
                }
            }
        }
        return entidade;
    }

    @Override
    public ArrayList lista() throws SQLException {
        ArrayList<ClienteModel> entidades = new ArrayList();
        try (Connection conexao = ConexaoMySQL.GetConexaoBD()) {
            String SQL;
            if (Has_Status)
                //SQL= "select * from " + getTabela() + " where NivelAcesso= 1";
            SQL= "select * from " + getTabela() + " where _Status > 0 and  NivelAcesso="+ EnumTipoUsuarios.CLIENTE.getCode();
            else
                SQL= "select * from " + getTabela() +" where  NivelAcesso="+ EnumTipoUsuarios.CLIENTE.getCode();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        ClienteModel entidade = (ClienteModel) preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }

        return entidades;
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
        String SQL= "Update " + getTabela() + " Set Nome=?,Telefone=?,Email=?,Senha=?,_Status=?,NivelAcesso=? where id="+ "\"" + user.getId() + "\"";
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
        stmt.setByte(6, EnumTipoUsuarios.CLIENTE.getCode());
        stmt.setByte(7,user.getNivel());

    }
    @Override
    protected void PrepareStatementUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        UsuarioModel user = (UsuarioModel) entidade;
        stmt.setString(1,user.getNome());
        stmt.setString(2,user.getTelefone());
        stmt.setString(3,user.getLogin());
        stmt.setString(4,user.getSenha());
        stmt.setByte(5, EnumTipoUsuarios.CLIENTE.getCode());
        stmt.setByte(6,user.getNivel());

    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        ClienteModel entidade = new ClienteModel();
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
