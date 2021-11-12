package br.com.fitcontrol.fitcontrol.dao.Cliente;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;

import java.sql.*;
import java.util.ArrayList;

public class ClienteMySQLDAO<E extends Entidade> extends MySQLDAO {
    public ClienteMySQLDAO() {
        super(ClienteModel.class);
        super.Has_Status=true;
        setTabela("tbClientes");
    }

    @Override
    public Entidade localiza(String codigo) throws SQLException {
        ClienteModel entidade = null;
        try (Connection conexao = ConexaoMySQL.GetConexaoBD()) {
            String SQL;
            if (Has_Status)
                SQL= "select * from " + getTabela() + " where Id = ? and _Status>0";
            else
                SQL= "select * from " + getTabela() + " where Id = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, codigo);
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
            SQL= "select * from " + getTabela() + " where _Status > 0 ";
            else
                SQL= "select * from " + getTabela() ;
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
        ClienteModel user=(ClienteModel)entidade;
        String SQL= "Insert into " + getTabela() + "(id,Nome,Telefone,Email,_Status) values (?,?,?,?,?)";
        return SQL;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        ClienteModel user=(ClienteModel)entidade;
        String SQL= "Update " + getTabela() + " Set Nome=?,Telefone=?,Email=? where id="+ user.getId();
        return SQL;
    }

    @Override
    protected void PrepareStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        ClienteModel user = (ClienteModel) entidade;
        stmt.setString(1,user.getId());
        stmt.setString(2,user.getNome());
        stmt.setString(3,user.getTelefone());
        stmt.setString(4,user.getLogin());
        stmt.setByte(5, (byte)1);

    }
    @Override
    protected void PrepareStatementUpdate(Entidade entidade, PreparedStatement stmt) throws SQLException {
        ClienteModel user = (ClienteModel) entidade;
        stmt.setString(1,user.getNome());
        stmt.setString(2,user.getTelefone());
        stmt.setString(3,user.getLogin());
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        ClienteModel entidade = new ClienteModel();
        try {
            entidade.setId(rs.getString("id"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setTelefone(rs.getString("Telefone"));
            entidade.setLogin(rs.getString("Email"));
            entidade.setStatus(rs.getByte("_Status"));
            entidade.setPontos(rs.getInt("Pontos"));
        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entidade;
    }
}
